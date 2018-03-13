/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String tweet = "sample tweet";
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
        String header = "Tweet,AT_Mention,Adjective,Determiner,Emoticon,Other,Nominal_Verbal,ProperNoun_Verbal,CommonNoun,ProNoun,Preposition,Adverb,Nominal_Possesive,VerbParticle,URL,Verb,Existential,Existential_Verbal,ProperNoun_Possesive,ProperNoun,Interjection,Hashtag,Numeral,Conjunction,Punctuation,Discourse_marker,Category";
        getWordList();
        TwitterPOS twitterPOS = new TwitterPOS();
        File inputFile = new File("input.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            File outputFile = new File("output.csv");
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

    private void pOSGenerator(String line, BufferedWriter bw) throws IOException, ClassNotFoundException {
        String modelFilename = "/cmu/arktweetnlp/model.20120919";
        Tagger tagger = new Tagger();
        tagger.loadModel(modelFilename);
        String tweet = line.substring(1, line.length() - 3);
        List<Tagger.TaggedToken> taggedTokens = tagger.tokenizeAndTag(tweet);
        Map<String, Integer> tagset = getTagset();
        for (Tagger.TaggedToken token : taggedTokens) {
            tagset.put(token.tag, tagset.get(token.tag) + 1);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("\n\"").append(tweet).append("\",");
        for (Map.Entry<String, Integer> entry : tagset.entrySet()) {
            builder.append(entry.getValue()).append(",");
        }
        bw.write(builder.toString());
        bw.flush();
    }
}
