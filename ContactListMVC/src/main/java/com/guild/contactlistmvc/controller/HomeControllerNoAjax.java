/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guild.contactlistmvc.controller;

import com.guild.contactlistmvc.dao.ContactListDao;
import com.guild.contactlistmvc.model.Contact;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeControllerNoAjax {

    // Reference to our DAO
    private ContactListDao dao;

    // Use annotation driven constructor injection to inject a DAO implementation
    // into our controller
    @Inject
    public HomeControllerNoAjax(ContactListDao dao) {
        this.dao = dao;
    }

    // This endpoint simply returns the name of the view that will serve as
    // the main landing page for the new functionality.  The name of that view
    // is displayContactListNoAjax.jsp.
    //
    // NOTE: The RequestMapping value, the name of the method, and the name
    //       of the JSP are all the same in this case (displayContactListNoAjax).
    //       THIS IS NOT A REQUIREMENT!!!  These names can be all different if
    //       you want them to be.
    //
    // NOTE: This method does takes a Model object as a parameter.  This is 
    //       because this method gets a list of all the Contact objects from the
    //       DAO.  We need to place this list on the Model so that Spring MVC
    //       can pass the list of Contacts on to the view component.  We'll
    //       use JSTL tags to iterate through the list and print the Contact
    //       information to the screen.
    @RequestMapping(value = "/displayContactListNoAjax", method = RequestMethod.GET)
    public String displayContactListNoAjax(Model model) {
        // Get the list of all Contacts
        List<Contact> cList = dao.getAllContacts();
        // Put the list of all Contacts on the Model so Spring MVC can pass it
        // along to the view
        model.addAttribute("contactList", cList);
        // Return the logical view name
        return "displayContactListNoAjax";
    }

    // This endpoint simply returns the name of the view that will display the
    // New Contact Form (newContactFormNoAjax.jsp).
    //
    // NOTE: This method takes no parameters because it does not need to look
    //       at the incoming request or put anything on the model.
    @RequestMapping(value = "displayNewContactFormNoAjax", method = RequestMethod.GET)
    public String displayNewContactFormNoAjax() {
        return "newContactFormNoAjax";
    }

    // This endpoint gets the submitted form data from the HttpServletRequest,
    // creates a new Contact object, sets the fields on the new Contact
    // object appropriately, add the Contact to the DAO, and then redirects
    // to the displayContactListNoAjax controller endpoint.
    @RequestMapping(value = "/addNewContactNoAjax", method = RequestMethod.POST)
    public String addNewContactNoAjax(HttpServletRequest req) {
        // Get all of the form data from the request
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String company = req.getParameter("company");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        // Create a new Contact and set all the fields
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setCompany(company);
        contact.setEmail(email);
        contact.setPhone(phone);

        // Add the Contact to the DAO
        dao.addContact(contact);

        // Redirect to the displayContactListNoAjax controller endpoint - we must
        // use the redirect: here so that Spring MVC routes us to the controller
        // endpoint and not directly to a JSP.
        return "redirect:displayContactListNoAjax";
    }

    // This method gets the id of the Contact to be deleted from the
    // HttpServletRequest and then asks the DAO to delete the Contact.  When
    // finished, it redirects to the displayContactListNoAjax controller
    // endpoint.
    @RequestMapping(value = "/deleteContactNoAjax", method = RequestMethod.GET)
    public String deleteContactNoAjax(HttpServletRequest req) {
        // Get the id of the contact to be deleted from the HttpServletRequest
        int contactId = Integer.parseInt(req.getParameter("contactId"));

        // Ask DAO to delete the given contact
        dao.removeContact(contactId);

        // Redirect to the displayContactListNoAjax controller endpoint - we must
        // use the redirect: here so that Spring MVC routes us to the controller
        // endpoint.
        return "redirect:displayContactListNoAjax";
    }

    // This method gets the id of the Contact to be edited from the HttpServletRequest,
    // retrieves the specified Contact from the DAO, puts the retrieved Contact
    // on the Model, and returns the name of the view that will display the 
    // Contact data in the Edit Form.
    @RequestMapping(value = "/displayEditContactFormNoAjax", method = RequestMethod.GET)
    public String displayEditContactFormNoAjax(HttpServletRequest req, Model model) {
        // Get the id of the Contact to be edited
        int contactId = Integer.parseInt(req.getParameter("contactId"));
        // Get the Contact from the DAO
        Contact contact = dao.getContactById(contactId);
        // Put the Contact on the Model
        model.addAttribute("contact", contact);

        return "editContactFormNoAjax";
    }

    // This method uses the @ModelAttribute annotation to tell Spring to
    // convert the submitted form data into a Contact object for us.  The method
    // then hands the Contact to the DAO for update, and finally redirects to
    // the displayContactListNoAjax controller endpoint.
    @RequestMapping(value = "/editContactNoAjax", method = RequestMethod.POST)
    // #1 and #2 - new annotation and parameter
    public String editContactNoAjax(@Valid @ModelAttribute("contact") Contact contact,
            BindingResult result) {

        // #3 - Redisplay the edit form if we find any validation errors
        if (result.hasErrors()) {
            return "editContactFormNoAjax";
        }

        // #4 - if there are no errors, update the contact as normal        
        dao.updateContact(contact);
        return "redirect:displayContactListNoAjax";
    }
}
