/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package machinlearningproj;

import cmu.arktweetnlp.Tagger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RoopTeja
 */
public class TwitterPOS {

    public static void main(String[] args) throws IOException{
        TwitterPOS twitterPOS = new TwitterPOS();
        //twitterPOS.tweetTagger();
        //String tweet = "RT @nationalpost: Canada confirms its first case of sexually transmitted Zika virus, in Ontario https://t.co/EepaamiPPi https://t.co/5hhEj.";
        String tweet = "RT @CDCgov: The best way to prevent is to prevent mosquito bites. https://t.co/REnmYVYc3l";
        String modelFilename = "/cmu/arktweetnlp/model.20120919";
        Tagger tagger = new Tagger();
        tagger.loadModel(modelFilename);
        List<Tagger.TaggedToken> taggedTokens = tagger.tokenizeAndTag(tweet);
        for (Tagger.TaggedToken token : taggedTokens) {
            System.out.println(""+token.tag);
            System.out.println(""+token.token);
            System.out.println("------------------------");
        }
    }

    private void tweetTagger() {
        //String header = "Tweet,AT_Mention,Adjective,Determiner,Emoticon,Other,Nominal_Verbal,ProperNoun_Verbal,CommonNoun,ProNoun,Preposition,Adverb,Nominal_Possesive,VerbParticle,URL,Verb,Existential,Existential_Verbal,ProperNoun_Possesive,ProperNoun,Interjection,Hashtag,Numeral,Conjunction,Punctuation,Discourse_marker,Category";
        String header = "Tweet,symptoms,microcephaly,birth,causes,defects,treatment,new,drug,vaccine,scientists,case,first,spread,olympics,brazil,fight,funding,congress,house,mosquito,birth defects,causes microcephaly,microcephaly birth,defects cdc,health officials,zika treatment,treatment zika,clinically approved,antiviral drug,drug sofosbuvir,puerto rico,pregnant women,first case,rio olympics,confirms first,white house,puerto rico,funding fight,public health,billion funding, category";
        //String header = "Tweet,treatment,health,symptoms,cdc,first,fight,microcephaly,new,funding,us,olympics,rio,amp,people,get,johnson,dustin,police,news,bacteria,birth defects,puerto rico,health officials,causes microcephaly,pregnant women,first case,white house,cdc says,microcephaly birth,public health,dustin johnson,rio olympics,body parts,profile common,birth defect,common birth,skip olympics,rory mcilroy,raises profile,super bacteria, relevant";
        getWordList();
        TwitterPOS twitterPOS = new TwitterPOS();
        //File inputFile = new File("D:\\Study\\Knoesis\\Zika\\Data\\09-20-2016\\AnnotatedTweets.csv");
        File inputFile = new File("D:\\Study\\MS Courses\\Current Literature in Empirical Analysis\\Data\\AnnotatedTweetsCatProcessed.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            File outputFile = new File("D:\\Study\\MS Courses\\Current Literature in Empirical Analysis\\Data\\AnnotatedTweetsCatPro.csv");
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            FileWriter fw = new FileWriter(outputFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(header);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                twitterPOS.pOSGenerator(sCurrentLine, bw);
            }
            bw.close();
            br.close();
        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
    }

    private Map getTagset() {
        Map<String, Integer> tagset = new LinkedHashMap();
        tagset.put("N", 0);
        tagset.put("O", 0);
        tagset.put("^", 0);
        tagset.put("S", 0);
        tagset.put("Z", 0);
        tagset.put("V", 0);
        tagset.put("L", 0);
        tagset.put("M", 0);
        tagset.put("A", 0);
        tagset.put("R", 0);
        tagset.put("!", 0);
        tagset.put("D", 0);
        tagset.put("P", 0);
        tagset.put("&", 0);
        tagset.put("T", 0);
        tagset.put("X", 0);
        tagset.put("Y", 0);
        tagset.put("#", 0);
        tagset.put("@", 0);
        tagset.put("U", 0);
        tagset.put("E", 0);
        tagset.put("$", 0);
        tagset.put(",", 0);
        tagset.put("G", 0);
        tagset.put("~", 0);
        return tagset;
    }

    private Map getWordset() {
        Map<String, Integer> wordset = new LinkedHashMap<>();
        wordset.put("symptoms", 0);
        wordset.put("microcephaly", 0);
        wordset.put("birth", 0);
        wordset.put("causes", 0);
        wordset.put("defects", 0);
        wordset.put("treatment", 0);
        wordset.put("new", 0);
        wordset.put("drug", 0);
        wordset.put("vaccine", 0);
        wordset.put("scientists", 0);
        wordset.put("case", 0);
        wordset.put("first", 0);
        wordset.put("spread", 0);
        wordset.put("olympics", 0);
        wordset.put("brazil", 0);
        wordset.put("fight", 0);
        wordset.put("funding", 0);
        wordset.put("congress", 0);
        wordset.put("house", 0);
        wordset.put("mosquito", 0);
        wordset.put("birth defects", 0);
        wordset.put("causes microcephaly", 0);
        wordset.put("microcephaly birth", 0);
        wordset.put("defects cdc", 0);
        wordset.put("health officials", 0);
        wordset.put("zika treatment", 0);
        wordset.put("treatment zika", 0);
        wordset.put("clinically approved", 0);
        wordset.put("antiviral drug", 0);
        wordset.put("drug sofosbuvir", 0);
        wordset.put("puerto rico", 0);
        wordset.put("pregnant women", 0);
        wordset.put("first case", 0);
        wordset.put("rio olympics", 0);
        wordset.put("confirms first", 0);
        wordset.put("white house", 0);
        wordset.put("puerto rico", 0);
        wordset.put("funding fight", 0);
        wordset.put("public health", 0);
        wordset.put("billion funding", 0);
        /*wordset.put("treatment", 0);
        wordset.put("health", 0);
        wordset.put("symptoms", 0);
        wordset.put("cdc", 0);
        wordset.put("first", 0);
        wordset.put("fight", 0);
        wordset.put("microcephaly", 0);
        wordset.put("new", 0);
        wordset.put("funding", 0);
        wordset.put("us", 0);
        wordset.put("olympics", 0);
        wordset.put("rio", 0);
        wordset.put("amp", 0);
        wordset.put("people", 0);
        wordset.put("get", 0);
        wordset.put("johnson", 0);
        wordset.put("dustin", 0);
        wordset.put("police", 0);
        wordset.put("news", 0);
        wordset.put("bacteria", 0);
        wordset.put("birth defects", 0);
        wordset.put("puerto rico", 0);
        wordset.put("health officials", 0);
        wordset.put("causes microcephaly", 0);
        wordset.put("pregnant women", 0);
        wordset.put("first case", 0);
        wordset.put("white house", 0);
        wordset.put("cdc says", 0);
        wordset.put("microcephaly birth", 0);
        wordset.put("public health", 0);
        wordset.put("dustin johnson", 0);
        wordset.put("rio olympics", 0);
        wordset.put("body parts", 0);
        wordset.put("profile common", 0);
        wordset.put("birth defect", 0);
        wordset.put("common birth", 0);
        wordset.put("skip olympics", 0);
        wordset.put("rory mcilroy", 0);
        wordset.put("raises profile", 0);
        wordset.put("super bacteria", 0);*/
        return wordset;
    }

    private List getWordList() {
        List<String> wordList = new LinkedList();
        wordList.add("symptoms");
        wordList.add("microcephaly");
        wordList.add("birth");
        wordList.add("causes");
        wordList.add("defects");
        wordList.add("treatment");
        wordList.add("new");
        wordList.add("drug");
        wordList.add("vaccine");
        wordList.add("scientists");
        wordList.add("case");
        wordList.add("first");
        wordList.add("spread");
        wordList.add("olympics");
        wordList.add("brazil");
        wordList.add("fight");
        wordList.add("funding");
        wordList.add("congress");
        wordList.add("house");
        wordList.add("mosquito");
        wordList.add("birth defects");
        wordList.add("causes microcephaly");
        wordList.add("microcephaly birth");
        wordList.add("defects cdc");
        wordList.add("health officials");
        wordList.add("zika treatment");
        wordList.add("treatment zika");
        wordList.add("clinically approved");
        wordList.add("antiviral drug");
        wordList.add("drug sofosbuvir");
        wordList.add("puerto rico");
        wordList.add("pregnant women");
        wordList.add("first case");
        wordList.add("rio olympics");
        wordList.add("confirms first");
        wordList.add("white house");
        wordList.add("funding fight");
        wordList.add("public health");
        wordList.add("billion funding");
        /*wordList.add("treatment");
        wordList.add("health");
        wordList.add("symptoms");
        wordList.add("cdc");
        wordList.add("first");
        wordList.add("fight");
        wordList.add("microcephaly");
        wordList.add("new");
        wordList.add("funding");
        wordList.add("us");
        wordList.add("olympics");
        wordList.add("rio");
        wordList.add("amp");
        wordList.add("people");
        wordList.add("get");
        wordList.add("johnson");
        wordList.add("dustin");
        wordList.add("police");
        wordList.add("news");
        wordList.add("bacteria");
        wordList.add("birth defects");
        wordList.add("puerto rico");
        wordList.add("health officials");
        wordList.add("causes microcephaly");
        wordList.add("pregnant women");
        wordList.add("first case");
        wordList.add("white house");
        wordList.add("cdc says");
        wordList.add("microcephaly birth");
        wordList.add("public health");
        wordList.add("dustin johnson");
        wordList.add("rio olympics");
        wordList.add("body parts");
        wordList.add("profile common");
        wordList.add("birth defect");
        wordList.add("common birth");
        wordList.add("skip olympics");
        wordList.add("rory mcilroy");
        wordList.add("raises profile");
        wordList.add("super bacteria");*/
        return wordList;
    }

    private void pOSGenerator(String line, BufferedWriter bw) throws IOException, ClassNotFoundException {
        String modelFilename = "/cmu/arktweetnlp/model.20120919";
        Tagger tagger = new Tagger();
        tagger.loadModel(modelFilename);
        String tweet = line.substring(1, line.length() - 3);
        String val = line.substring(line.length() - 1, line.length());
        List<Tagger.TaggedToken> taggedTokens = tagger.tokenizeAndTag(tweet);
        Map<String, Integer> tagset = getTagset();
        for (Tagger.TaggedToken token : taggedTokens) {
            tagset.put(token.tag, tagset.get(token.tag) + 1);
        }
        Map<String, Integer> wordset = getWordset();
        List<String> wordList = getWordList();
        for (int i = 0; i < wordList.size(); i++) {
            int count = getOccurrences(tweet, wordList.get(i));
            wordset.put(wordList.get(i), wordset.get(wordList.get(i)) + count);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n\"").append(tweet).append("\",");
        /*for (Map.Entry<String, Integer> entry : tagset.entrySet()) {
            builder.append(entry.getValue()).append(",");
        }*/
        for (Map.Entry<String, Integer> entry : wordset.entrySet()) {
            builder.append(entry.getValue()).append(",");
        }
        builder.append(val);
        bw.write(builder.toString());
        bw.flush();
    }

    private int getOccurrences(String tweet, String key) {
        int lastIndex = 0;
        int count = 0;
        while (lastIndex != -1) {
            lastIndex = tweet.toLowerCase().indexOf(key.toLowerCase(), lastIndex);
            if (lastIndex != -1) {
                count++;
                lastIndex += key.length();
            }
        }
        return count;
    }
}
