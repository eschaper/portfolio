package com.guild.vendingmachinemvc.model;

import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class Item {
    
    @NotEmpty(message = "You must supply a value for slot")
    //@Length(max = 20, message = "Slot must be no more than 20 characters")
    private String slot;
    @NotEmpty(message = "You must supply a value for name")
    //@Length(max = 20, message = "Name must be no more than 20 characters")
    private String name;
    @NotNull(message = "You must supply a value for cost")
    @Min(value = 0, message = "Cost must be greater than zero")
    private int cost;
    //@NotNull(message = "You must supply a value for amount")
    @Min(value = 0, message = "Amount must be greater than zero")
    private int amount;

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return this.name + " " + this.cost;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + this.cost;
        hash = 19 * hash + this.amount;
        hash = 19 * hash + Objects.hashCode(this.slot);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.cost != other.cost) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.slot, other.slot)) {
            return false;
        }
        return true;
    }

}
