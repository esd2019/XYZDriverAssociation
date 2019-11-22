/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jennistly
 */
public class payments {
    private int id;
    private String mem_id;
    private String email;
    private String type_of_payment;
    private double amount ;
    private String date;
    
    public payments(int id, String mem_id, String email, String type_of_payment, double amount, String date){
        this.id = id;
        this.mem_id = mem_id;
        this.email = email;
        this.type_of_payment = type_of_payment;
        this.amount = amount;
        this.date =date;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMem_id(){
        return mem_id;
    }
    
    public void setMem_id(String mem_id){
        this.mem_id = mem_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType(){
        return type_of_payment;
    }
    
    public void setType(){
        this.type_of_payment = type_of_payment;
    }
    
    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
