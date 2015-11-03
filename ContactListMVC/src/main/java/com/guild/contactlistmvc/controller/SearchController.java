package com.guild.contactlistmvc.controller;

import com.guild.contactlistmvc.dao.ContactListDao;
import com.guild.contactlistmvc.dao.SearchTerm;
import com.guild.contactlistmvc.model.Contact;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    private ContactListDao dao;

    // @Inject and @Autowired are synonyms
    // This annotation tells the Spring Framework to hand an object of type
    // ContactListDao to this constructor when it creates an instance of this
    // class (which happens when the web application starts). If there is no
    // object of type ContactListDao defined in the Spring application context ,
    // Spring Framework will throw an exception.
    @Inject
    public SearchController(ContactListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }

    // This method will be invoked by Spring MVC when it sees a POST request for
    // ContactListMVC/search/contacts. It translates the entered search terms
    // from the given Map<String, String> to a Map<SearchTerm, String>, passes
    // the search criteria to the DAO, and returns the search results to the
    // caller.
    @RequestMapping(value = "search/contacts", method = RequestMethod.POST)
    @ResponseBody
    public List<Contact> searchContacts(@RequestBody Map<String, String> searchMap) {

        // Create the map of search criteria to send to the DAO
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        // Determine which search terms have values, translate the String
        // keys into SearchTerm enums, and set the corresponding values
        // appropriately.
        String currentTerm = searchMap.get("firstName");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.FIRST_NAME, currentTerm);
        }
        currentTerm = searchMap.get("lastName");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.LAST_NAME, currentTerm);
        }
        currentTerm = searchMap.get("company");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.COMPANY, currentTerm);
        }
        currentTerm = searchMap.get("email");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.EMAIL, currentTerm);
        }
        currentTerm = searchMap.get("phone");
        if (!currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.PHONE, currentTerm);
        }
        return dao.searchContacts(criteriaMap);
    }
}
