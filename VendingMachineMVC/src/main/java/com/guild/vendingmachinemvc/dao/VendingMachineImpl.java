package com.guild.vendingmachinemvc.dao;

import com.guild.vendingmachinemvc.model.Item;
import com.guild.vendingmachinemvc.model.VendRequest;
import com.guild.vendingmachinemvc.model.VendResponse;
import java.util.*;

public class VendingMachineImpl implements VendingMachineDao {

    private Map<String, Item> contents = new HashMap<>();
    private int totalChange;

    @Override
    public int getTotalChange() {
        return totalChange;
    }

    @Override
    public void setTotalChange(int change) {
        totalChange = change;
    }

    @Override
    public Item getItemBySlot(String slot) {
        return contents.get(slot);
    }

    @Override
    public void updateItem(Item item) {
        contents.put(item.getSlot(), item);
    }

    @Override
    public void removeItem(String slot) {
        contents.remove(slot);
    }

    @Override
    public void addItem(Item item) {
        contents.put(item.getSlot(), item);
    }

    @Override
    public List<Item> getContents() {

        if (contents.isEmpty()) {
            List<Item> items = new ArrayList<>();
            Item item = new Item();
            item.setAmount(10);
            item.setCost(200);
            item.setName("Snickers");
            item.setSlot("A1");
            items.add(item);
            contents.put(item.getSlot(), item);

            Item item2 = new Item();
            item2.setAmount(10);
            item2.setCost(150);
            item2.setName("Mars");
            item2.setSlot("B4");
            items.add(item2);
            contents.put(item2.getSlot(), item2);

            Item item3 = new Item();
            item3.setAmount(10);
            item3.setCost(75);
            item3.setName("Milky Way");
            item3.setSlot("C9");
            items.add(item3);
            contents.put(item3.getSlot(), item3);

            Item item4 = new Item();
            item4.setAmount(10);
            item4.setCost(200);
            item4.setName("Snickers");
            item4.setSlot("D10");
            items.add(item4);
            contents.put(item4.getSlot(), item4);

            return items;

        } else {

            return new ArrayList(contents.values());

        }
    }

    @Override
    public VendResponse vendRequest(VendRequest request) {
        VendResponse response = new VendResponse();
        String outcome;
        int status;
        Item item = getItemBySlot(request.getSlot());

        if (item == null) {
            outcome = "Item not found.";
            status = 1;
        } else if (getTotalChange() < item.getCost()) {
            outcome = "Insufficent funds. Input more money.";
            status = 2;
        } else {
            item.setAmount(item.getAmount() - 1);
            updateItem(item);
            
            setTotalChange(getTotalChange() - item.getCost());
            outcome = "Dispensing " + item.getName();
            status = 3;
        }

        response.setOutcome(outcome);
        response.setStatus(status);
        return response;

    }

    @Override
    public String returnChange(int totalChange) {
        int quarters, dimes, nickels, pennies;
        String change = "";

        if (totalChange == 0) {
            change += "There is no change.";
        } else {
            change += "Your change is:";
            if (totalChange >= 25) {
                quarters = totalChange / 25;
                totalChange %= 25;
                change += " " + quarters + " quarter";
                if (quarters > 1) {
                    change += "s";
                }

            }
            if (totalChange >= 10) {
                dimes = totalChange / 10;
                totalChange %= 10;
                change += " " + dimes + " dime";
                if (dimes > 1) {
                    change += "s";
                }
            }
            if (totalChange >= 5) {
                nickels = totalChange / 5;
                totalChange %= 5;
                change += " " + nickels + " nickel";
                if (nickels > 1) {
                    change += "s";
                }
            }
            if (totalChange >= 1) {
                pennies = totalChange;
                if (pennies > 1) {
                    change += " " + pennies + " pennies";
                } else {
                    change += " " + pennies + " penny";
                }
            }
        }
        return change;
    }

}
