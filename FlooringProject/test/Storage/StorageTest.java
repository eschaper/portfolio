/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Storage;

import java.io.File;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class StorageTest {
    
    public StorageTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of writeToFile method, of class Storage.
     */
    @Test
    public void testWriteToFile_Map() throws Exception {
        System.out.println("writeToFile");
        Storage instance = new Storage();
        File file = new File("TestFiles/09252015");
        
        HashMap<String, ArrayList<String[]>> elements = new HashMap<>();
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] array =  {"2","3","87","789","124", "74", "13"};
        String[] array2 = {"1","2","3","4","5","6","7"};
        arrayList.add(array);
        arrayList.add(array2);
        
        elements.put("TestFiles/09252015", arrayList);
        instance.writeToFile(elements);
        
        assertTrue(file.exists());
    }

    /**
     * Test of writeToFile method, of class Storage.
     */
    @Test
    public void testWriteToFile_String_ArrayList() throws Exception {
        System.out.println("writeToFile");
        Storage instance = new Storage();
        File file = new File("TestFiles/testtest");
        
        ArrayList<String[]> arrayList = new ArrayList<>();
        String[] array =  {"2","3","87","789","124", "74", "13"};
        String[] array2 = {"1","2","3","4","5","6","7"};
        arrayList.add(array);
        arrayList.add(array2);
        String filename = "TestFiles/testtest";
        
        instance.writeToFile(filename, arrayList);
        assertTrue(file.exists());
        
    }

    /**
     * Test of readFromFile method, of class Storage.
     */
    @Test
    public void testReadFromFile() {
        ArrayList<String[]> testArray = new ArrayList<>();
        String[] array =  {"2","3","87","789","124", "74", "13"};
        String[] array2 = {"1","2","3","4","5","6","7"};
        testArray.add(array);
        testArray.add(array2);
        
        System.out.println("readFromFile");
        String filename = "TestFiles/09252015";
        Storage instance = new Storage();
        ArrayList<String[]> expResult = testArray;
        ArrayList<String[]> result = instance.readFromFile(filename);
        
        assertTrue(result.get(0)[0].equals(expResult.get(0)[0]));
        assertTrue(result.get(1)[0].equals(expResult.get(1)[0]));
        assertTrue(Arrays.equals(result.get(0), expResult.get(0)));
        
    }
    
}
