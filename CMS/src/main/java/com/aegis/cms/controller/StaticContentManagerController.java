package com.aegis.cms.controller;

import com.aegis.cms.dao.StaticDao;
import com.aegis.cms.model.StaticContent;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class StaticContentManagerController {

    private StaticDao dao;

    @RequestMapping(value = "/staticMan", method = RequestMethod.GET)
    public String displayStaticContentManager() {
        return "staticContentManager";
    }

    @Inject
    public StaticContentManagerController(StaticDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/header", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void setStaticContent(@RequestBody StaticContent cont) {
        dao.addStaticContent(cont);
    }

    @RequestMapping(value = "/header", method = RequestMethod.GET)
    @ResponseBody
    public StaticContent getStaticContent() {
        return dao.getStaticContent();
    }

    @RequestMapping(value = "/header", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED) 
    @ResponseBody
    public void updateStaticContent(@RequestBody StaticContent cont) {
        dao.updateStaticContent(cont);
    }

    @RequestMapping(value = "/header", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStaticContent(StaticContent cont) {
        dao.deleteStaticContent(cont);
    }

}
