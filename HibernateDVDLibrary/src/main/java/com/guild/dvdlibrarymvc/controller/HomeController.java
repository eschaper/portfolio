package com.guild.dvdlibrarymvc.controller;

import com.guild.dvdlibrarymvc.dao.DvdLibraryDao;
import com.guild.dvdlibrarymvc.model.Dvd;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    
    private DvdLibraryDao dao;
    
    @Inject
    public HomeController(DvdLibraryDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
    
    //get dvd
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd getAddress(@PathVariable("id") int id) {
        return dao.getDvdById(id);
    }
    
    //add dvd
    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Dvd createDvd(@Valid @RequestBody Dvd dvd) {
        dao.addDvd(dvd);
        return dvd;
    }
    
    //remove dvd
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDvd(@PathVariable("id") int id) {
        dao.removeDvd(id);
    }
    
    //update dvd
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT) 
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putDvd(@PathVariable("id") int id, @Valid @RequestBody Dvd dvd) {
        dvd.setDvdId(id);
        dao.updateDvd(dvd);
    }
    
    //get all dvds
    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<Dvd> getAllDvds() {
        return dao.getAllDvds();
    }
    
}
