/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author jennistly
 */
public class Payment {
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id = count.incrementAndGet();
    private int mem_id;
    private String type;
    private double amount ;
    private String date;
    private String description;
    private String code;
    private int outstanding_id;
    
    
   
    
    public Payment(int id, int mem_id,int oustanding_id, String type, double amount, String code, String description,String date){
        this.id = id;
        this.mem_id = mem_id;
        this.type = type;
        this.amount = amount;
        this.date =date;
        this.description = description;
        this.code = code;
        this.outstanding_id = outstanding_id;
        
    }
    public Payment(){
        
    }
    public Payment(int mem_id,int oustanding_id, String type, double amount, String code, String description,String date){
        this.mem_id = mem_id;
        this.type = type;
        this.amount = amount;
        this.date =date;
        this.description = description;
        this.code = code;
        this.outstanding_id = outstanding_id;
        
    }

    public int getOutstanding_id() {
        return outstanding_id;
    }

    public void setOutstanding_id(int outstanding_id) {
        this.outstanding_id = outstanding_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMem_id() {
        return mem_id;
    }

    public void setMem_id(int mem_id) {
        this.mem_id = mem_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
    
}
