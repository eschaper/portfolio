package com.guild.dvdlibrarymvc.dao;

import com.guild.dvdlibrarymvc.model.Dvd;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DVDLibraryEmmaDessa implements DvdLibraryDao {

    private Map<Integer, Dvd> shelf = new HashMap<>();
    private int idCounter = 0;
    
    @Override
    public Dvd addDvd(Dvd movie) {
        movie.setDvdId(idCounter);
        idCounter++;
        shelf.put(movie.getDvdId(), movie);
        return movie;
    }

    @Override
    public void removeDvd(int dvdId) {        
        shelf.remove(dvdId);
    }
    
    @Override
    public List<Dvd> getAllDvds() {
        Collection<Dvd> copy = shelf.values();
        
        return new ArrayList(copy);
    }

    @Override
    public Dvd getDvdById(int dvdId) {        
        return shelf.get(dvdId);
    }
    
    @Override
    public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
        
        String titleTerm = criteria.get(SearchTerm.TITLE);
        String releaseDateTerm = criteria.get(SearchTerm.RELEASE_DATE);
        String mpaaTerm = criteria.get(SearchTerm.MPAA_RATING);
        String directorTerm = criteria.get(SearchTerm.DIRECTOR);
        String studioTerm = criteria.get(SearchTerm.STUDIO);
        String noteTerm = criteria.get(SearchTerm.NOTE);
        
        Predicate<Dvd> titleSearch;
        Predicate<Dvd> releaseDateSearch;
        Predicate<Dvd> mpaaSearch;
        Predicate<Dvd> directorSearch;
        Predicate<Dvd> studioSearch;
        Predicate<Dvd> noteSearch;
        
        Predicate<Dvd> truePredicate = (d) -> {return true;};
        
        titleSearch = (titleTerm == null || titleTerm.isEmpty())
                        ? truePredicate
                        : (d) -> d.getTitle().equals(titleTerm);
        
        releaseDateSearch = (releaseDateTerm == null || releaseDateTerm.isEmpty())
                        ? truePredicate
                        : (d) -> d.getReleaseDate().equals(releaseDateTerm);
        
        mpaaSearch = (mpaaTerm == null || mpaaTerm.isEmpty())
                        ? truePredicate
                        : (d) -> d.getMpaaRating().equals(mpaaTerm);
        
        directorSearch = (directorTerm == null || directorTerm.isEmpty())
                        ? truePredicate
                        : (d) -> d.getDirector().equals(directorTerm);
        
        studioSearch = (studioTerm == null || studioTerm.isEmpty())
                        ? truePredicate
                        : (d) -> d.getStudio().equals(studioTerm);
        
        noteSearch = (noteTerm == null || noteTerm.isEmpty()) 
                        ? truePredicate
                        : (d) -> d.getNote().equals(noteTerm);
        
        return shelf
                .values()
                .stream()
                .filter(titleSearch
                        .and(releaseDateSearch)
                        .and(mpaaSearch)
                        .and(directorSearch)
                        .and(studioSearch)
                        .and(noteSearch))
                .collect(Collectors.toList());
    }
    
    @Override
    public void updateDvd(Dvd dvd) {
        shelf.put(dvd.getDvdId(), dvd);
    }
    
}
//   public List<Dvd> oldestMovie() {
//        int oldestYear =  shelf
//                .stream()                
//                .mapToInt(d -> (d.getReleaseDate().getYear()))
//                .min()
//                .getAsInt();
//        
//        return shelf
//                .stream()
//                .filter(a -> a.getReleaseDate().getYear() == oldestYear)
//                .collect(Collectors.toList());
//                
//    }
//    
//    public List<Dvd> newestMovie() {
//        int newestYears =  shelf
//                .stream()                
//                .mapToInt(a -> a.getReleaseDate().getYear())
//                .max()
//                .getAsInt();
//        
//        return shelf
//                .stream()
//                .filter(a -> a.getReleaseDate().getYear() == newestYears)
//                .collect(Collectors.toList());
//                
//    }
//
//    public int dvdCount() {
//        return shelf.size();
//    }
//
//    public int titleCount(String find) {
//        int count = 0;
//
//        count = (int) shelf
//                .stream()
//                .filter(a -> a.getTitle().equalsIgnoreCase(find))
//                .count();
//        //.map((temp) -> temp.getTitle()).filter((str) -> (find.equals(str))).map((_item) -> 1).reduce(count, Integer::sum);
//        return count;
//    }
//        
//    public ArrayList<Dvd> findByDate(int yearsBack) {
//        SimpleDateFormat calF = new SimpleDateFormat("yyyy");
//        int years = Integer.parseInt(calF.format(Calendar.getInstance().getTime()));
//        years -= yearsBack;
//
//        return (ArrayList<Dvd>) shelf
//                .stream()
//                .filter(a -> a.getReleaseDate().getYear() > yearsBack)
//                .collect(Collectors.toList());
//    }
//    
  

//    public double findAverageAge() {
//        SimpleDateFormat calF = new SimpleDateFormat("yyyy");
//        final int years = parseInt(calF.format(Calendar.getInstance().getTime()));
//        
//
//        return shelf
//                .stream()
//                .mapToDouble(a -> (years - parseInt(a.getReleaseDate())))
//                .average()
//                .getAsDouble();
//                
//        
//    }
    
//    public void populate(ArrayList<Dvd> inputShelf) {
//
//        shelf.addAll(inputShelf);
//    }
//