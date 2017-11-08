package flesch;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Flesch {

    private FileReader fr;
    private Integer numberOfWords;
    private Integer numberOfSent;
    private Integer numberOfSyl;

    public static void main(String args[]) throws Exception {
        Flesch fl = new Flesch(args[0]);

        BufferedReader br = new BufferedReader(fl.fr);
        String s;
        while((s = br.readLine()) != null) {
        }

        countSyls("real");
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
    static int countSyls(String string) {
        Integer numOfSyls = 0;
        char sylls[] = {'a', 'e', 'i', 'o', 'u', 'y'};

        String strArr[] = string.split("[aeiouy]");

        System.out.println(Arrays.toString(strArr));

        // Count numbet strings in the array and remove illegals
        boolean bFrist = true;
        for (String s : strArr) {
            if (bFrist || (s != null && s.length() > 0)) {
                numOfSyls++;
            }
            bFrist = false;
        }

        if (0 == numOfSyls) {
            return 1;
        }
        return numOfSyls;
    }
}
