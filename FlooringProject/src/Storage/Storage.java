package Storage;

import java.io.*;
import java.util.*;

/**
 *
 * @author Emma/James
 */
public class Storage {
    
    // Takes Map<Filename, ArrayList<String[]>
    public void writeToFile(Map<String, ArrayList<String[]>> elements) throws IOException {
        Set<String> filenames = elements.keySet();
        String outString;

        for (String fn : filenames) {
            PrintWriter out = new PrintWriter(new FileWriter(fn));
            ArrayList<String[]> arrayList = elements.get(fn);

            for (int i = 0; i < arrayList.size(); i++) {
                String[] strArray = arrayList.get(i);

                outString = strArray[0];
                for (int j = 1; j < strArray.length; j++) {
                    outString += "," + strArray[j];

                }

                out.println(outString);
                outString = "";
            }

            out.flush();
            out.close();
        }

    }
    
    // Takes filename and ArrayList<String[]>
    public void writeToFile(String filename, ArrayList<String[]> elements) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        String outString = "";

        for (int i = 0; i < elements.size(); i++) {
            String[] strArray = elements.get(i);

            outString = strArray[0];
            for (int j = 1; j < strArray.length; j++) {
                outString += "," + strArray[j];

            }

            out.println(outString);
            outString = "";
        }

        out.flush();
        out.close();

    }

    public ArrayList<String[]> readFromFile(String filename) {
        try {
            Scanner in = new Scanner(new BufferedReader(new FileReader(filename)));

            ArrayList<String[]> elements = new ArrayList<>();
            String line;
            String[] splitline;

            while (in.hasNextLine()) {
                line = in.nextLine();
                splitline = line.split(",");
                elements.add(splitline);
            }

            return elements;

        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

}
