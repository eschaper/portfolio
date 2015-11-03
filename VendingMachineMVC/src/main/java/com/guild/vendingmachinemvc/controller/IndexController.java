package com.guild.vendingmachinemvc.controller;

import com.guild.vendingmachinemvc.dao.VendingMachineDao;
import com.guild.vendingmachinemvc.model.Item;
import com.guild.vendingmachinemvc.model.VendRequest;
import com.guild.vendingmachinemvc.model.VendResponse;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class IndexController {

    private VendingMachineDao dao;

    @Inject
    public IndexController(VendingMachineDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String displayIndexPage() {
        return "index";
    }
    
    @RequestMapping(value = "/item", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Item addItem(@Valid @RequestBody Item item) {
        dao.addItem(item);
        return item;
    }
    
    // update item
    @RequestMapping(value = "/vend", method = RequestMethod.POST)
    @ResponseBody
    public VendResponse vendRequest(@Valid @RequestBody VendRequest request) {        
        return dao.vendRequest(request);        
    }

    // get all items
    @RequestMapping(value = "/machine", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> getAllItems() {
        return dao.getContents();
    }
    
    // send money to dao
    @RequestMapping(value = "/money/{amt}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void putMoney(@PathVariable("amt") int money) {
        dao.setTotalChange(dao.getTotalChange() + money);
    }
    
    // get money from dao
    @RequestMapping(value = "/money", method = RequestMethod.GET)
    @ResponseBody
    public int getMoney() {
        return dao.getTotalChange();
    }
    
    // return change and set money to 0
    @RequestMapping(value = "/changeReturn", method = RequestMethod.PUT)
    @ResponseBody
    public String returnMoney() {    
        int change = dao.getTotalChange();
        
        dao.setTotalChange(0);
        return (dao.returnChange(change));
    }
    
}
