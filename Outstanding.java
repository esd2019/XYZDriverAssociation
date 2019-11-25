/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ninhthelam
 */
public class Outstanding {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id = count.incrementAndGet();
    private int user_id;
    private String email;
    private String code;
    private String description;
    private double amount;
    private String status = "Pay";
    
    public Outstanding(int id, int user_id, String email, String code, String description, double amount, String status){
        this.id = id;
        this.user_id = user_id;
        this.email = email;
        this.code = code;
        this.description = description;
        this.amount = amount;
        this.status = status;
               
        
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Outstanding(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
            
            
    
}
