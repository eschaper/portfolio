package com.guild.dvdlibrarymvc.dao;

import com.guild.dvdlibrarymvc.model.Dvd;
import java.util.List;
import java.util.Map;

public interface DvdLibraryDao {

    // add the given Dvd to the data store
    public Dvd addDvd(Dvd dvd);

    // remove the Dvd with the given id from the data store
    public void removeDvd(int dvdId);

    // update the given Dvd in the data store
    public void updateDvd(Dvd dvd);

    // retrieve all Dvds from the data store
    public List<Dvd> getAllDvds();

    // retrieve the Dvd with the given id from the data store
    public Dvd getDvdById(int dvdId);

    // search for Dvds by the given search criteria values
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria);

}
