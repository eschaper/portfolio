package com.guild.vendingmachinemvc.dao;

import com.guild.vendingmachinemvc.model.Item;
import com.guild.vendingmachinemvc.model.VendRequest;
import com.guild.vendingmachinemvc.model.VendResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class VendingMachineDbImpl implements VendingMachineDao {

    //leave change calculation in memory
    private int totalChange;
    private static final String SQL_ADD_ITEM
            = "insert into machine (slot, item_name, cost, amount) "
            + "values (?, ?, ?, ?)";
    private static final String SQL_DELETE_ITEM
            = "delete from machine "
            + "where slot = ?";
    private static final String SQL_SELECT_ITEM
            = "select * "
            + "from machine where slot = ?";
    private static final String SQL_UPDATE_ITEM
            = "update machine "
            + "set slot = ?, item_name = ?, cost = ?, amount = ? "
            + "where slot = ?";
    private static final String SQL_SELECT_ALL_ITEMS
            = "select * from machine";
    private JdbcTemplate jdbcTemplate;

    public void setjdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int getTotalChange() {
        return totalChange;
    }

    @Override
    public void setTotalChange(int change) {
        totalChange = change;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addItem(Item item) {
        jdbcTemplate.update(SQL_ADD_ITEM,
                item.getSlot(),
                item.getName(),
                item.getCost(),
                item.getAmount());
    }

    @Override
    public void removeItem(String slot) {
        jdbcTemplate.update(SQL_DELETE_ITEM, slot);
    }

    @Override
    public Item getItemBySlot(String slot) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(), slot);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getSlot(),
                item.getName(),
                item.getCost(),
                item.getAmount(),
                item.getSlot());
    }

    @Override
    public List<Item> getContents() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ITEMS, new ItemMapper());
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

    private static final class ItemMapper implements ParameterizedRowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setSlot(rs.getString("slot"));
            item.setName(rs.getString("item_name"));
            item.setCost(rs.getInt("cost"));
            item.setAmount(rs.getInt("amount"));

            return item;
        }
    }
}
