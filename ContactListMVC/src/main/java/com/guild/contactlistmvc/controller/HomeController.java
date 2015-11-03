package com.guild.contactlistmvc.controller;

import com.guild.contactlistmvc.dao.ContactListDao;
import com.guild.contactlistmvc.model.Contact;
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
public class HomeController {

    // The controller uses the dao to do all the heavy lifting of storing
    // and retrieving Contacts
    private ContactListDao dao;

    

    // @Inject and @Autowired are synonyms
    // This annotation tells the Spring Framework to hand an object of type
    // ContactListDao to this constructor when it creates an instance of this
    // class (which happens when the web application starts). If there is no
    // object of type ContactListDao defined in the Spring application context ,
    // Spring Framework will throw an exception.
    @Inject
    public HomeController(ContactListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
    // This method will be invoked by Spring MVC when it sees a request for
    // ContactListMVC/mainAjaxPage.
    @RequestMapping(value = {"/mainAjaxPage"}, method = RequestMethod.GET)
    public String displayMainAjaxPage() {
        
        // This method does nothing except return the logical name of the
        // view component (/jsp/home.jsp) that should be invoked in response
        // to this request.
        return "mainAjaxPage";
    }
    
    // This method will be invoked by Spring MVC when it sees a GET request for
    // ContactListMVC/contact/<some-contact-id>. It retrieves the Contact
    // associated with the given contact id (or null if no such Contact
    // exists).
    //
    // @ResponseBody indicates that the object returned by this method should
    // be put in the body of the response going back to the caller.
    //
    // @PathVariable indicates that the portion of the URL path marked by curly
    // braces {...} should be stripped out, converted to an int and passed into
    // this method when it is invoked.
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact getContact(@PathVariable("id") int id) {

        // retrieve the Contact associated with the given id and return it
        return dao.getContactById(id);
    }

    // This method will be invoked by Spring MVC when it sees a POST request for
    // ContactListMVC/contact. It persists the given Contact to the data layer.
    //
    // @ResponseStatus tells Spring MVC to return an HTTP CREATED status upon success
    //
    // @ResponseBody indicates that the object returned by this method should
    // be put in the body of the response going back to the caller.
    //
    // @RequestBody indicates that we expect a Contact object
    // in the body of the incoming request.
    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Contact createContact(@Valid @RequestBody Contact contact) {

        // persist the incoming contact
        dao.addContact(contact);

        // The addContact call to the dao assigned a contactId to the incoming
        // Contact and set that value on the object. Now we return the updated
        // object to the caller.
        return contact;
    }

    // This method will be invoked by Spring MVC when it sees a DELETE request
    // for ContactListMVC/contact/<some-contact-id>. It deletes the Contact
    // associated with the give id from the data layer (it does nothing if there
    // is no such Contact).
    //
    // @ResponseStatus tells Spring MVC to return HTTP NO_CONTENT from this call
    // because this method has no return value.
    //
    // @PathVariable indicates that the portion of the URL path marked by curly
    // braces {...} should be stripped out, converted to an int and passed into
    // this method when it is invoked.
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") int id) {

        // remove the Contact associated with the given id from the data layer
        dao.removeContact(id);
    }

    // This method will be invoked by Spring MVC when it sees a PUT request for
    // ContactListMVC/contact/<some-contact-id>. It updates the given Contact
    // to the data layer.
    //
    // @ResponseStatus tells Spring MVC to return HTTP NO_CONTENT from this call
    // because this method has no return value.
    //
    // @PathVariable indicates that the portion of the URL path marked by curly
    // braces {...} should be stripped out, converted to an int and passed into
    // this method when it is invoked.
    //
    // @RequestBody indicates that we expect a Contact object in the body of the
    // incoming request.
    @RequestMapping(value = "/contact/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putContact(@PathVariable("id") int id, @Valid @RequestBody Contact contact) {

        // set the value of the PathVariable id on the incoming Contact object
        // to ensure that a) the contact id is set on the object and b) that
        // the value of the PathVariable id and the Contact object id are the
        // same.
        contact.setContactId(id);

        // update the contact
        dao.updateContact(contact);
    }

    // This method will be invoked by Spring MVC when it sees a GET request for
    // ContactListMVC/contacts. It retrieves all of the Contacts from the
    // data layer and returns them in a List.
    //
    // @ResponseBody indicates that the List returned by this method should
    // be put in the body of the response going back to the caller.
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {

        // get all of the Contacts from the data layer
        return dao.getAllContacts();
    }
}
