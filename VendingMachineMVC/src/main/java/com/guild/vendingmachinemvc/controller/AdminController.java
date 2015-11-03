package com.guild.vendingmachinemvc.controller;

import com.guild.vendingmachinemvc.dao.VendingMachineDao;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
    
    private VendingMachineDao dao;
    
    @Inject
    public AdminController(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String displayAdminPage() {
        return "admin";
    }
    
    
}
