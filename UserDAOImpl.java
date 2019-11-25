/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import model.Claim;
import model.Outstanding;
import model.Payment;
import model.User;

/**
 *
 * @author tuan
 */
public class UserDAOImpl implements UserDAO {

    Connection cons = DBConnect.getConnection();

    @Override
    public boolean checkAccountExist(String email) {
        PreparedStatement ps = null;
        String sql = "select email from USERS where email='" + email + "'";
        try {
            ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("ERROR CHECKING EMAIL");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public void insertAccount(User user) {
        PreparedStatement ps = null;
        String sql = "insert into USERS (id,fullname, email,status,balance,address,password, account, code,month,year_1,dob) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = cons.prepareCall(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getStatus());
            ps.setDouble(5, user.getBalance());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getAccount());
            ps.setInt(9, user.getCode());
            ps.setInt(10, user.getMonth());
            ps.setInt(11, user.getYear());
            ps.setString(12, user.getDob());
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public boolean checkLogin(String email, String password) {
        PreparedStatement ps = null;
        String sql = "select * from USERS where email='" + email + "' AND password='" + password + "'";
        try {
            ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("ERROR CHECKING LOGIN");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public Outstanding getOutstandingDetailFromEmail(String email) {
        PreparedStatement ps = null;
        Outstanding outstanding = new Outstanding();
        try {

            String sql = "SELECT * FROM OUTSTANDING WHERE email='" + email + "' and status='Due'";
            ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                outstanding.setId(rs.getInt("id"));
                outstanding.setUser_id(rs.getInt("user_id"));
                outstanding.setEmail(rs.getString("email"));
                outstanding.setCode(rs.getString("fee_code"));
                outstanding.setDescription(rs.getString("desciption"));
                outstanding.setAmount(rs.getDouble("amount"));

            }

        } catch (SQLException ex) {

            System.err.println("NO outstanding balance DETAIL FOUND");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return outstanding;
    }

    @Override
    public boolean checkAccountExistBaseOnEmailAndPassword(String email, String password) {
        PreparedStatement ps = null;
        String sql = "select email from USERS where email='" + email + "' and password='" + password + "'";
        try {
            ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.err.println("ERROR CHECKING EXIST");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public String getUserNameFromUserID(String id) {
        PreparedStatement ps = null;
        String sql = "select username from users where userid='" + id + "'";
        String username = "";
        try {
            ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
            }

        } catch (Exception e) {
            System.err.println("ERROR GET USERNAME");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return username;
    }

    @Override
    public int getUserIDFromEmail(String email) {
        PreparedStatement ps = null;
        int id = 0;
        if (!email.equals("")) {

            String sql = "select id from USERS where email='" + email + "'";

            try {
                ps = cons.prepareCall(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                }

            } catch (Exception e) {
                System.err.println("ERROR GET USERID");
            } finally {
                try {
                    cons.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return id;
    }

    @Override
    public User getUserDetailByEmail(String email) {
        PreparedStatement ps = null;
        User user = new User();
        try {

            String sql = "SELECT * FROM USERS WHERE email='" + email + "'";
            ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFullname(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getString("dob"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
                user.setAccount(rs.getInt("account"));
                user.setCode(rs.getInt("code"));
                user.setMonth(rs.getInt("month"));
                user.setYear(rs.getInt("year_1"));

            }

        } catch (SQLException ex) {

            System.err.println("NO USER DETAIL FOUND");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public void updateAccount(User user) {

        PreparedStatement ps = null;
        String sql = "UPDATE USERS SET id=?, fullname=?, email=?, status=?, balance=?, address=?, password=?, account=?, code=?, month=?, year_1=?, dob=? WHERE email='" + user.getEmail() + "'";
        try {
            ps = cons.prepareCall(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFullname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getStatus());
            ps.setDouble(5, user.getBalance());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getPassword());
            ps.setInt(8, user.getAccount());
            ps.setInt(9, user.getCode());
            ps.setInt(10, user.getMonth());
            ps.setInt(11, user.getYear());
            ps.setString(12, user.getDob());
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getUserEmailByUserID(String id) {
        PreparedStatement ps = null;
        String sql = "select email from user where userid='" + id + "'";
        String email = "";
        try {
            ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
            }

        } catch (Exception e) {
            System.err.println("ERROR GET EMAIL");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return email;
    }

    @Override
    public void insertClaim(Claim user) {
        PreparedStatement ps = null;
        String sql = "insert into CLAIMS (id,email, rational, date,amount,status) VALUES (?,?,?,?,?,?)";
        try {
            ps = cons.prepareCall(sql);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getRational());
            ps.setString(4, user.getDate());
            ps.setDouble(5, user.getAmount());
            ps.setString(6, user.getStatus());
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertPayment(Payment user) {
        PreparedStatement ps = null;
        String sql = "insert into PAYMENTS (id, mem_id, outstanding_id, type, amount, code, description,date) VALUES (?,?,?,?,?,?,?,?)";
        try {
            ps = cons.prepareCall(sql);
            ps.setInt(1, user.getId());
            ps.setInt(2, user.getMem_id());
            ps.setInt(3, user.getOutstanding_id());
            ps.setString(4, user.getType());
            ps.setDouble(5, user.getAmount());
            ps.setString(6, user.getCode());
            ps.setString(7, user.getDescription());
            ps.setString(8, user.getDate());
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public ArrayList<Outstanding> getOutstandingByEmail(String email) {
        ArrayList<Outstanding> list = new ArrayList<>();
        PreparedStatement ps = null;

        //int id = 0;
        try {

            String sql = "SELECT * FROM OUTSTANDING WHERE email='" + email + "'";
            ps = cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Outstanding outstanding = new Outstanding();
                outstanding.setId(rs.getInt("id"));
                outstanding.setUser_id(rs.getInt("user_id"));
                outstanding.setEmail(rs.getString("email"));
                outstanding.setCode(rs.getString("fee_code"));
                outstanding.setDescription(rs.getString("desciption"));
                outstanding.setAmount(rs.getDouble("amount"));
                outstanding.setStatus(rs.getString("status"));
                list.add(outstanding);

            }

        } catch (SQLException ex) {

            System.err.println("NO outstanding balance DETAIL FOUND");
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;

    }
}
