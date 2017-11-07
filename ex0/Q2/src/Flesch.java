import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Flesch {

    private FileReader fr;
    private Integer numberOfWords;
    private Integer numberOfSent;
    private Integer numberOfSyl;

    static int main(String args[]) throws Exception {
        Flesch fl = new Flesch(args[0]);

        BufferedReader br = new BufferedReader(fr);
        String s;
        while((s = br.readLine()) != null) {
            System.out.println(s);
        }
        return 0;
    }

    private Flesch(String fileName) throws Exception {
        fr = new FileReader(fileName);
        numberOfWords = 0;
        numberOfSent = 0;
        numberOfSyl = 0;
    }

    private void finish() throws IOException {
        fr.close();
    }

    /**
     * @modifies
     * @effects Count number of syllables in a word (s must be a word)
     * @return Return the number of syllables in the word s
     */
    static int countSils(String string) {
        Integer numOfSyls = 0;
        char sylls[] = {'a', 'e', 'i', 'o', 'u', 'y'};

        String strArr[] = string.split("^(?!aeiouy)");

        // Count numbet strings in the array and remove illegals

        if (0 == numOfSyls) {
            return 1;
        }
        return numOfSyls;
    }
}
