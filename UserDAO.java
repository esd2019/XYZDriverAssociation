/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Claim;
import model.Outstanding;
import model.Payment;
import model.User;


public interface UserDAO {
    public boolean checkAccountExist(String username);
    public void insertAccount(User user);
    public boolean checkLogin(String email, String password);
    public Outstanding getOutstandingDetailFromEmail(String email);
    public boolean checkAccountExistBaseOnEmailAndPassword(String email,String password);
    public String getUserNameFromUserID(String id);
    public int getUserIDFromEmail(String email);
    public User getUserDetailByEmail(String email);
    public void updateAccount(User user);
    public String getUserEmailByUserID(String id);
    public void insertClaim(Claim user);
    public void insertPayment(Payment user);
    public ArrayList<Outstanding> getOutstandingByEmail(String email);
   
}
