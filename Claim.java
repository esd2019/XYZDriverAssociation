/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ninhthelam
 */
public class Claim {
    private int id;
    private String email;
    private String rational;
    private String date;
    private int amount;
    private String status;
    
    public Claim(int id, String email, String rational, String date, int amount, String status){
        this.email = email;
        this.rational = rational;
        this.status = status;
        this.amount = amount;
        this.id = id;
        this.date =date;
        
    }
    public Claim() {
    }
    public Claim( String email, String rational, String date, int amount, String status){
        this.email = email;
        this.rational = rational;
        this.status = status;
        this.amount = amount;
        this.date = date;
       
        
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

    public String getRational() {
        return rational;
    }

    public void setRational(String rational) {
        this.rational = rational;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
