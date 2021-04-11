import java.io.*;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;


public class Best10 {

    protected static HashMap<String, Integer> dictionary = new HashMap<String, Integer>();

    public static void ReadFile(String filename) {
        try (BufferedReader  in = new BufferedReader(new FileReader(filename));){
            // BufferedReader   in = new BufferedReader(new FileReader(filename));

			String line = in.readLine();
			while( line != null ){
			    System.out.println(line);
                line = in.readLine();
                String[] words = line.split("\\s+");

                for (int i =0; i < words.length; i++) {
                    if (!dictionary.containsKey(words[i])) {
                        dictionary.put(words[i], 1);
                    } else {
                        int oldSize = dictionary.get(words[i]);
                        dictionary.put(words[i], oldSize + 1);
                    }
                }
			}
            in.close();

        } catch (NullPointerException e){
            System.out.println("End of file");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file \"" + filename + "\" for reading");
        } catch (IOException e) {
            System.out.println("Error: Cannot read from file \"" + filename + "\"");
        }
    }

    public static void WriteFile(String filename, KeyValue[] topTen) {
        try (PrintWriter out= new PrintWriter(new FileWriter(filename))) {
            // PrintWriter out= new PrintWriter(new FileWriter(filename));

            for (int i =0; i < topTen.length; i++) {
                out.println(topTen[i]);
            }

            out.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: Cannot open file \"" + filename + "\" for writing.");
        } catch (IOException e) {
            System.out.println("Error: Cannot write to file \"" + filename + "\".");
        }
    }

    public static void main(String[] args) {
        
        ReadFile("Question2.txt");
        ArrayList<KeyValue> tempArray = new ArrayList<KeyValue>();
        Set<String> tempSet = dictionary.keySet();
        String[] keyArray = new String[tempSet.size()];

        tempSet.toArray(keyArray);

        for (int i = 0; i < keyArray.length; i++) {
            tempArray.add(new KeyValue(keyArray[i], dictionary.get(keyArray[i])));
        }

        Collections.sort(tempArray);
        
        KeyValue[] top10 = new KeyValue[10];
        int x = 0;
        for (int i = (tempArray.size() - 1); i > (tempArray.size() - 11); i--) {
            top10[x] = tempArray.get(i);
            x++;
        }
        
        WriteFile("best10.txt", top10);
    }
}