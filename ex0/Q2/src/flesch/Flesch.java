package flesch;

import java.io.FileReader;
import java.io.BufferedReader;

/**
 * counts sentences, words, and syllables in a given file.
 */
public class Flesch {

    private FileReader fr;
    private Integer numberOfWords;
    private Integer numberOfSent;
    private Integer numberOfSyl;

    public static void main(String args[]) throws Exception {
        String fileName = args[0];
        FileReader fr = new FileReader(fileName);
        Integer numberOfWords = 0;
        Integer numberOfSent = 0;
        Integer numberOfSyl = 0;

        BufferedReader br = new BufferedReader(fr);
        String text_sentances = "";
        String text_words = "";
        String s;
        while((s = br.readLine()) != null) {
            text_sentances += s;
            text_words += s;
            text_words += ' ';
        }
        // count sentences
        String sentenceArr[] = text_sentances.split("[.,;?!]");
        for (String sentence : sentenceArr) {
            if (!sentence.isEmpty()) {
                numberOfSent += 1;
            }
        }
        // count words
        String wordArr[] = text_words.split("[()*&^%$#@!_+\\-={}?;:',.\\[\\]\" ]");
        for (String word : wordArr) {
            if (!word.isEmpty()) {
                numberOfWords += 1;
                numberOfSyl += countSyls(word);
            }
        }

        Double fleschScore = 206.835 - (84.6 * numberOfSyl.floatValue() / numberOfWords.floatValue()) - (1.015 *
                numberOfWords.floatValue() / numberOfSent.floatValue());

        // Go Sinoff, Go Sinoff!

        System.out.println("File " + fileName + " contains " + numberOfSent + " sentences, " + numberOfWords +
                " words, and " + numberOfSyl + " syllables. the Flesch Score of this file is " + fleschScore + ".");
        fr.close();

    }

    /**
     * @requires a string containing one word
     * @modifies
     * @effects Count number of syllables in a word (s must be a word)
     * @return Return the number of syllables in the word s
     */
    static int countSyls(String string) {
        Integer numOfSyls = 0;
        if (string.endsWith("e")) {
            string = string.substring(0, string.length() - 1);
        }
        String sylArr[] = string.split("[^aAeEiIoOuUyY]");
        for (String s : sylArr) {
            if (!s.isEmpty()) {
                numOfSyls += 1;
            }
        }
        if (numOfSyls == 0) {
            numOfSyls = 1;
        }
        return numOfSyls;
    }
}
