/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guild.vendingmachinemvc.model;

/**
 *
 * @author apprentice
 */
public class VendRequest {
    
    // presence of money field is remnent from when program was 
    // keeping track of input money in memory in JavaScript
    private int money;
    private String slot;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
    
    
}
