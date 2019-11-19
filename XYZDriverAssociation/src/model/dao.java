/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

/**
 * Database access object
 */
public class Dao {

    //meta
    
    private String driver;
            
    private String databaseURL;
    private String username;
    private String password;
    
    //data
    
    private Connection connection = null; //connection object for accessing database
    private DatabaseMetaData databaseMetaData = null; // object for holding database metadata
    
    private Statement statement = null; //statement for querying database
    private ResultSet resultSet = null; //object for holding database results
    private ResultSetMetaData resultSetMetaData = null; //object for holding result set metadata
    
    private ArrayList<String[]> arrayListResultSet = new ArrayList<>();
    
    //debug
    
    private ArrayList<String> log = new ArrayList<>();
    
    //---------------------------------Construtor-------------------------------
    
    public Dao(){
        this.databaseURL = "";
        this.username = "";
        this.password = "";
    }
    
    public Dao(String databaseURL, String username, String password){
        this.databaseURL = databaseURL;
        this.username = username;
        this.password = password;
    }
    
    public Dao(String driver, String databaseURL, String username, String password){
        this.driver = driver;
        this.databaseURL = databaseURL;
        this.username = username;
        this.password = password;
    }
    
    //-------------------------------Getter Setter------------------------------
    
    //meta
    
    public void setDriver(String driver) { this.driver = driver; } 

    public void setDatabaseURL(String databaseURL) { this.databaseURL = databaseURL; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    
    public void setAllLoginValues(String databaseURL, String username, String password){
        this.databaseURL = databaseURL;
        this.username = username;
        this.password = password;
    } 
    
    //data

    public String getDriver() { return driver; }
    
    public Connection getConnection() { return connection; }
    
    //---------------------------------Functions--------------------------------

    //connect
    
    public boolean loadDriver(){
        
        try {
            Class.forName(driver);
            log.add("Driver Present");
        } 
        catch (ClassNotFoundException ex) {
            //Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
            log.add("Driver not found.");
            return false;
        }
        return true;
    }
    
    public boolean connect(){
        
        //Stop if databaseURL or username or both not set
        if(databaseURL.equals("") || username.equals("")){
            log.add("Database URL and username can't be blank.");
            return false;
        }
        
        //try to connect
        try {
            connection = DriverManager.getConnection(databaseURL, username, password);
            databaseMetaData = connection.getMetaData();
            log.add("Connection Successful");
        } catch (SQLException ex) {
            log.add("Database URL, username, or password may be wrong.");
            return false;
        }
        return true;
    } 
    
    //database operations
    
    public void printMetadata(){
        try{
            databaseMetaData.getTablePrivileges(null, null, null);
        }
        catch(SQLException e){
            log.add("Failed to print database metadata");
        }
    }
    
    public boolean executeQuery(String query){
        try{
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            log.add("Statement Created.");
            resultSet = statement.executeQuery(query);
            log.add("resultSet generated.");
            resultSetMetaData = resultSet.getMetaData();
            log.add("Query executed.");
            return true;
        }
        catch(SQLException e){
            log.add("Failed to execute query: " + e.getMessage());
        }
        return false;
    }
    public boolean update(String query){
        try{
            statement = connection.createStatement();
            int count = statement.executeUpdate(query);
            return true;
        }
        catch(SQLException e){
            log.add("Failed to execute update: " + e.getMessage());
        }
        return false;
    }
    
    public User createUser(String name, String address, Date dob){
        
        User u = null;
        
        //username
        String[] nameSplit = name.split(" ");
        String id = nameSplit[0];
        if (executeQuery("SELECT * FROM APP.USERS WHERE id='"+ id +"'")){
            Random r = new Random();
            for (int i = 0; i < 3; i++) id += Integer.toString(r.nextInt(10));
        }
        
        //password
        PasswordGenerator p = new PasswordGenerator();
        p.generatePassword(10);
        String pass = p.getPassword();
        
        //status
        String status = "APPLIED";
        
        //date of registration
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        java.sql.Date dor = new java.sql.Date(currentDate.getTime());
        
        //insert user
        boolean validQuery = update("INSERT INTO APP.USERS VALUES('" + id + "','" + pass + "','" + status + "')");
        
        //insert member
        if (validQuery) 
            validQuery = update("INSERT INTO APP.MEMBERS VALUES('" + 
                    id + "','" + name + "','" + address + "','" + dob + "','" + dor + "','" + status + "'," + 0 + ")");
        
        //if member insert fails delete newly added user
        if (validQuery == false) {
            Boolean validQueryTwo = update("DELETE FROM APP.USERS WHERE ID='" + id + "'");
            if (validQueryTwo == false) log.add("Failed to remove from user table after failed to add member table");
        }
        
        if (validQuery) u = new User(id,pass,status);
        
        return u;
    }
    
    public User getUser(String id, String password){
        User u = null;
        
        boolean validQuery = executeQuery("SELECT * FROM APP.USERS WHERE ID='" + id + "' AND PASSWORD='"+ password + "'");

        if (validQuery)
            try{
                //If there are any results
                if (resultSet.last()){
                    log.add("Result Set set to last entry.");
                    //if there is one record
                    if (resultSet.getRow() == 1) u = new User(id, password,resultSet.getString("STATUS"));
                    
                    else log.add("Multiple results returned for user search.");
                }
                else log.add("Invalid login details");
            }
            catch(SQLException e) { log.add("SQL error getting user."); }
            
        return u;
    }
    public Member getMember(String id){
        Member m = null;
        
        boolean validQuery = executeQuery("SELECT * FROM APP.MEMBERS WHERE ID='" + id + "'");
        
        if (validQuery)
            try{
                //If there are any results
                if (resultSet.last()){
                    log.add("Result Set set to last entry.");
                    //if there is one record
                    if (resultSet.getRow() == 1) {
                        String name = resultSet.getString("NAME");
                        String address = resultSet.getString("ADDRESS");
                        Date dob = resultSet.getDate("DOB");
                        Date dor = resultSet.getDate("DOR");
                        String status = resultSet.getString("STATUS");
                        double balance = resultSet.getDouble("BALANCE");
                        
                        m = new Member(id, name, address, dob, dor, status, balance);
                    }
                    
                    else log.add("Multiple results returned for user search.");
                }
                else log.add("Invalid member id");
            }
            catch(SQLException e) { log.add("SQL error getting user."); }
        
        return m;
    }
    public ArrayList getAllMembers(){
        ArrayList<User> members = new ArrayList<>();
        
        boolean validQuery = executeQuery("SELECT * FROM.APP.USERS WHERE STATUS='MEMBER'");
        
        if (validQuery)
            try{
                //for each result
                while(resultSet.next())
                    members.add(new User(resultSet.getString("ID"),resultSet.getString("PASSWORD"),resultSet.getString("STATUS")));
            }
            catch(SQLException e) { log.add("SQL error getting user."); }
        
        return members;
    }
    
    public boolean resultSetToArrayList() {
        
        //check if null
        if (resultSet == null || resultSetMetaData == null){
            if (resultSet == null) log.add("resultSet null.");
            if (resultSetMetaData == null) log.add("resultSetMetaData null.");
            return false;
        }
        
        arrayListResultSet = new ArrayList();
        try{
            int cols = resultSetMetaData.getColumnCount();
            log.add("column count retrieved for resultSetMetaData: " + cols);
            String[] row = new String[cols];
            
            //get header
            for (int col = 0; col < cols; col++){
                row[col] = resultSetMetaData.getColumnName(col+1); // Column indexes start from 1, disgusting...
                log.add("Header column added - " + col + " : " + row[col]);
            }
            arrayListResultSet.add(row.clone());
            log.add("Header added.");
            
            //get data
            while (resultSet.next()) { 
              for (int col = 0; col < cols; col++) {
                row[col] = resultSet.getString(col+1);
                log.add("Data column added - " + col + " : " + row[col]);
              } 
              arrayListResultSet.add(row.clone());
              log.add("Data added.");
            }                
        }
        catch(SQLException e){
            log.add("Failed to convert result set to arraylist.");
            return false;
        }
        
        return true;
    } //rsToList
    public boolean printArrayListResultSet(){
        
        //check if emtpy
        if(arrayListResultSet.isEmpty()){
            log.add("arrayList empty");
            return false;
        }
        
        try{
            int cols = resultSetMetaData.getColumnCount();
            
            System.out.println("ArrayList:");
            for (String[] row : arrayListResultSet){
                String r = "";
                for(int col = 0; col < cols; col++)
                    if(col == cols-1) r += row[col];
                    else r += row[col] += ", ";
                System.out.println(r);
            }
        }
        catch(SQLException e){
            log.add("Failed to print arrayListResultSet.");
            return false;
        }
        
        return true;
    }
    private String makeHtmlTable(ArrayList list) {
        StringBuilder b = new StringBuilder();
        String[] row;
        b.append("<table border=\"3\">");
        for (Object s : list) {
          b.append("<tr>");
          row = (String[]) s;
            for (String row1 : row) {
                b.append("<td>");
                b.append(row1);
                b.append("</td>");
            }
          b.append("</tr>\n");
        } // for
        b.append("</table>");
        return b.toString();
    }//makeHtmlTable
    
    //disconnect
    
    public boolean close(){
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
        } catch (Exception ex) {
            log.add("Error closing database access objects.");
            return false;
        }
        return true;
    }
    
    public boolean closeAll(){
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception ex) {
            log.add("Error closing database access objects.");
            return false;
        }
        return true;
    } //tested
    
    //-----------------------------------Debug----------------------------------
    
    public String getLastLogEntry(){
        if(!log.isEmpty()) return log.get(log.size()-1);
        return "No Log Entries";
    }
    
    public ArrayList<String> getLog(){
        return log;
    }
}