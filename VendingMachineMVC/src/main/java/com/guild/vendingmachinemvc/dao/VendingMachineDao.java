
package com.guild.vendingmachinemvc.dao;

import com.guild.vendingmachinemvc.model.Item;
import com.guild.vendingmachinemvc.model.VendRequest;
import com.guild.vendingmachinemvc.model.VendResponse;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {
    
    public void setTotalChange(int totalChange);
    
    public int getTotalChange();
    
    public Item getItemBySlot(String slot);
    
    public void updateItem(Item item);
    
    public void removeItem(String slot);

    public void addItem(Item item);
    
    public List<Item> getContents();

    public String returnChange(int totalChange);
    
    public VendResponse vendRequest(VendRequest request);
    
}
//    public int getTotalChange();
//
//    public void setTotalChange(int change);
//    
//    public void removeMoney(int change);
//
//    public void addMoney(int change);
//
//    