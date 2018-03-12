/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.shingle.ShingleFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.util.Version;
import sun.util.locale.StringTokenIterator;

/**
 *
 * @author RoopTeja
 */
public class NgramGenerator {

    private HashMap<String, Integer> bigram = new HashMap();

    public static void main(String[] args) {
        int count = 0;
        NgramGenerator ngramGenerator = new NgramGenerator();
        File inputFile = new File("D:\\Study\\MS Courses\\Current Literature in Empirical Analysis\\Data\\AnnotatedTweetsProcessed.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if(Float.parseFloat(sCurrentLine.substring(sCurrentLine.length()-1, sCurrentLine.length())) == 0) {
                    ngramGenerator.ngramGenerator(sCurrentLine.substring(1, sCurrentLine.length() - 3));
                    count++;
                }
            }
            br.close();
        } catch (IOException e) {

        }
        TreeMap<String, Integer> sortedMap = ngramGenerator.sortMapByValue(ngramGenerator.bigram);
        int index = 0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " - " + value);
            /*if(index == 20){
                break;
            }
            index++;*/
        }
        //System.out.println(sortedMap);
        System.out.println(count);
    }

    private TreeMap<String, Integer> sortMapByValue(HashMap<String, Integer> map) {
        Comparator<String> comparator = new ValueComparator(map);
        TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
        result.putAll(map);
        return result;
    }

    private void tweetTagger() {
        //String header = "Tweet,AT_Mention,Adjective,Determiner,Emoticon,Other,Nominal_Verbal,ProperNoun_Verbal,CommonNoun,ProNoun,Preposition,Adverb,Nominal_Possesive,VerbParticle,URL,Verb,Existential,Existential_Verbal,ProperNoun_Possesive,ProperNoun,Interjection,Hashtag,Numeral,Conjunction,Punctuation,Discourse_marker,women,pregnant,health,birth,mosquito,microcephaly,olympics,rio,fund,virus,Category";
        String header = "Tweet,AT_Mention,Adjective,Determiner,Emoticon,Other,Nominal_Verbal,ProperNoun_Verbal,CommonNoun,ProNoun,Preposition,Adverb,Nominal_Possesive,VerbParticle,URL,Verb,Existential,Existential_Verbal,ProperNoun_Possesive,ProperNoun,Interjection,Hashtag,Numeral,Conjunction,Punctuation,Discourse_marker,Category";
        File inputFile = new File("D:\\Study\\Knoesis\\Zika\\Data\\09-20-2016\\AnnotatedTweets.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            File outputFile = new File("D:\\Study\\MS Courses\\Current Literature in Empirical Analysis\\Data\\AnnotatedTweets.csv");
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(header);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {

            }
            bw.close();
            br.close();
        } catch (IOException e) {

        }
    }

    private void ngramGenerator(String line) {
        List<String> words = new ArrayList();
        words.add("is");
        words.add("a");
        words.add("and");
        CharArraySet stopWords = StopFilter.makeStopSet(Version.LUCENE_46, words, true);
        StringReader reader = new StringReader(line);
        StandardTokenizer source = new StandardTokenizer(Version.LUCENE_46, reader);
        TokenStream tokenStream = new StandardFilter(Version.LUCENE_46, source);
        StopFilter stopFilter = new StopFilter(Version.LUCENE_46, tokenStream, stopWords);
        ShingleFilter sf = new ShingleFilter(tokenStream);
        //sf.setMinShingleSize(2);
        sf.setMaxShingleSize(2);//
        sf.setOutputUnigrams(false);
        CharTermAttribute charTermAttribute = sf.addAttribute(CharTermAttribute.class);
        try {
            sf.reset();
            while (sf.incrementToken()) {
                //System.out.println(charTermAttribute.toString());
                String key = charTermAttribute.toString();
                int count = bigram.containsKey(key) ? bigram.get(key) : 0;
                bigram.put(key, count + 1);
            }
            sf.end();
            sf.close();
        } catch (IOException e) {
        }
    }

    class ValueComparator implements Comparator<String> {

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        public ValueComparator(HashMap<String, Integer> map) {
            this.map.putAll(map);
        }

        @Override
        public int compare(String s1, String s2) {
            if (map.get(s1) >= map.get(s2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
