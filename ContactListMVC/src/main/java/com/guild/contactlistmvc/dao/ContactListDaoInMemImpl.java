package com.guild.contactlistmvc.dao;

import com.guild.contactlistmvc.model.Contact;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactListDaoInMemImpl implements ContactListDao {

    // holds all of our Contact objects - simulates the database
    private Map<Integer, Contact> contactMap = new HashMap<>();

    // used to assign ids to Contacts - simulates the auto increment
    // primary key for Contacts in a database
    private static int contactIdCounter = 0;

    @Override
    public Contact addContact(Contact contact) {

        // assign the current counter values as the contactid
        contact.setContactId(contactIdCounter);

        // increment the counter so it is ready for use for the next contact
        contactIdCounter++;
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    @Override
    public void removeContact(int contactId) {
        contactMap.remove(contactId);
     }
    
    @Override
    public void updateContact(Contact contact) {
        contactMap.put(contact.getContactId(), contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        Collection<Contact> c = contactMap.values();
        return new ArrayList(c);
    }

    @Override
    public Contact getContactById(int contactId) {
        return contactMap.get(contactId);
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        
        // Get all the search terms from the map
        String firstNameCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameCriteria = criteria.get(SearchTerm.LAST_NAME);
        String companyCriteria = criteria.get(SearchTerm.COMPANY);
        String phoneCriteria = criteria.get(SearchTerm.PHONE);
        String emailCriteria = criteria.get(SearchTerm.EMAIL);
        
        // Declare all the predicate conditions 
        Predicate<Contact> firstNameMatches;
        Predicate<Contact> lastNameMatches;
        Predicate<Contact> companyMatches;
        Predicate<Contact> phoneMatches;
        Predicate<Contact> emailMatches;

        // Placeholder predicate - always returns true. Used for search terms
        // that are empty
        Predicate<Contact> truePredicate = (c) -> {
            return true;
        };
        
        // Assign values to predicates. If a given search term is empty, just
        // assign the default truePredicate, otherwise assign the predicate that
        // properly filters for the given term.
        firstNameMatches = (firstNameCriteria == null || firstNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getFirstName().equals(firstNameCriteria);
        
        lastNameMatches = (lastNameCriteria == null || lastNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getLastName().equals(lastNameCriteria);
        
        companyMatches = (companyCriteria == null || companyCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getCompany().equals(companyCriteria);
        
        phoneMatches = (phoneCriteria == null || phoneCriteria.isEmpty())
                ? truePredicate
                :(c) -> c.getPhone().equals(phoneCriteria);
        
        emailMatches = (emailCriteria == null || emailCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getEmail().equals(emailCriteria);
        
        // Return the list of Contacts that match the given criteria. To do this we
        // just AND all the predicates together in a filter operation.
        return contactMap
                        .values()
                        .stream()
                        .filter(firstNameMatches
                                .and(lastNameMatches)
                                .and(companyMatches)
                                .and(phoneMatches)
                                .and(emailMatches)
                                )
                        .collect(Collectors.toList());
    }
}
