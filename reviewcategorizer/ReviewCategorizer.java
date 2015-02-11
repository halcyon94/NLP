package reviewcategorizer;
/* 
Sheryan Resutov
Natural Language Processing
Professor Sable
Final Project
5/4/2014
*/
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.DocumentPreprocessor;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class ReviewCategorizer {

    public static void main(String args[]) throws IOException {
        HashMap<String, Double> Class1 = new HashMap();
        HashMap<String, Double> Class2 = new HashMap();
        HashMap<String, Double> Class7 = new HashMap();
        HashMap<String, Double> Class8 = new HashMap();
        HashMap<String, String> classMap = new HashMap();       //Stores current Categories
        HashMap<String, Double> flagHashMap = new HashMap();    //Stores words that already appeared in a doc
        String stringLabel;                                     //Stores the current word in a document
        String className;                                       //Stores the current Category name
        double count1=0.0,count2=0.0, sentenceCount1=0, sentenceCount2=0;        //Count of # of Docs in each Category
        int i=0; 
        String trainPath, testPath, corpusPath, predictionsPathU,predictionsPathB,bigramS=null,nextDoc;
        String[] keyArray = new String[]{"a","b","c","d","e","f"};                      //used later to access Category names in the classMap HSM
                Scanner path = new Scanner(System.in);
             //   System.out.println("Enter the absolute path for training file (No spaces at the end)");
             //   trainPath = path.nextLine();
                trainPath = "C://cygwin/home/skorpion/op_spam_v1.4/cv_p_fold3_train.labels";
             //   System.out.println("Enter the absolute path for testing file (No spaces at the end)");
             //   testPath = path.nextLine();
                testPath = "C://cygwin/home/skorpion/op_spam_v1.4/cv_p_fold3_test.list";
              //  System.out.println("Enter the absolute path for the corpuses (ie. C://TC_provided/ for us)(No spaces at the end)");
              //  corpusPath = path.nextLine();
                corpusPath = "C://cygwin/home/skorpion/op_spam_v1.4/";
                Scanner scannerIn = new Scanner(new File(trainPath));
                PTBTokenizer ptbt;
                while(scannerIn.hasNext()){
                    try {
                        nextDoc=scannerIn.next();
                        ptbt = new PTBTokenizer(new FileReader(corpusPath+"/"+nextDoc),new CoreLabelTokenFactory(), "");
                        DocumentPreprocessor dp = new DocumentPreprocessor(corpusPath+"/"+nextDoc);
                        className = scannerIn.next();
                        if(!(classMap.containsKey(className))){
                            classMap.put(className, keyArray[i]);
                            i++;
                            
                        }
                    switch (classMap.get(className)) {
                            case "a":  
                                count1++;
                                for (CoreLabel label; ptbt.hasNext(); ) {
                                    label = (CoreLabel) ptbt.next();
                                    stringLabel = label.toString();
                                    if(Class1.containsKey(stringLabel) && (!flagHashMap.containsKey(stringLabel))){
                                        Class1.put(stringLabel, Class1.get(stringLabel)+1);
                                        flagHashMap.put(stringLabel,1.0);
                                    } 
                                    if(!Class1.containsKey(stringLabel)){
                                        Class1.put(stringLabel,1.0);
                                        flagHashMap.put(stringLabel,1.0);
                                    }                  
                                }
                                for (List sentence : dp) {
                                    sentenceCount1++;
                                    for(int iter=0; iter<sentence.size(); iter++){
                                        if(iter==0){
                                            bigramS="<s> " + sentence.get(0);
                                        }
                                        if(iter>0 && iter<(sentence.size()-1)){
                                            bigramS=sentence.get(iter-1)+" "+sentence.get(iter);
                                        }
                                        if((iter==(sentence.size()-1))&&sentence.size()>1){
                                            bigramS=sentence.get(sentence.size()-2)+" <s>";
                                        }
                                        if((iter==(sentence.size()-1))&& sentence.size()==1){
                                            bigramS=sentence.get(0)+" <s>";
                                        } 
                                        if(Class7.containsKey(bigramS))
                                            Class7.put(bigramS, Class7.get(bigramS)+1);
                                        if(!Class7.containsKey(bigramS))
                                            Class7.put(bigramS,1.0);
                                    }  
                                }
                                flagHashMap.clear(); 
                                break;
                            case "b":  
                                count2++;
                                for (CoreLabel label; ptbt.hasNext(); ) {
                                    label = (CoreLabel) ptbt.next();
                                    stringLabel = label.toString();
                                    if(Class2.containsKey(stringLabel) && (!flagHashMap.containsKey(stringLabel))){
                                        Class2.put(stringLabel, Class2.get(stringLabel)+1);
                                        flagHashMap.put(stringLabel,1.0);
                                    } 
                                    if(!Class2.containsKey(stringLabel)){
                                        Class2.put(stringLabel,1.0);
                                        flagHashMap.put(stringLabel,1.0);
                                    }                  
                                }
                                for (List sentence : dp) {
                                    sentenceCount2++;
                                    for(int iter=0; iter<sentence.size(); iter++){
                                        if(iter==0){
                                            bigramS="<s> " + sentence.get(0);
                                        }
                                        if(iter>0 && iter<(sentence.size()-1)){
                                            bigramS=sentence.get(iter-1)+" "+sentence.get(iter);
                                        }
                                        if((iter==(sentence.size()-1))&& sentence.size()>1){
                                            bigramS=sentence.get(sentence.size()-2)+" <s>";
                                        }
                                        if((iter==(sentence.size()-1))&& sentence.size()==1){
                                            bigramS=sentence.get(0)+" <s>";
                                        } 
                                        if(Class8.containsKey(bigramS) /*&& (!flagHashMap.containsKey(bigramS))*/)
                                            Class8.put(bigramS, Class8.get(bigramS)+1);
                                        if(!Class8.containsKey(bigramS))
                                            Class8.put(bigramS,1.0);
                                    }  
                                }
                                flagHashMap.clear(); 
                                break;
                            
                        }
                    }   
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(ReviewCategorizer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }           
                scannerIn.close();
                //used to generate top bigrams 
/*              for (String key: Class7.keySet()){
                    if((Class7.get(key)>200)&&(Class7.get(key)<250)){
                        System.out.println(findClass("a",classMap)+" "+key+" "+Class7.get(key));
                    }
                }
                for (String key: Class8.keySet()){
                    if((Class8.get(key)>200) && (Class8.get(key)<250)){
                        System.out.println(findClass("b",classMap)+" "+key+" "+Class8.get(key));
                    }
                }       */   
                for (String key: Class7.keySet()){
                      String[] splited = key.split(" ");
                      if(splited[0].equals("<s>")){
                        Class7.put(key, Math.log10((Class7.get(key))/sentenceCount1));
                      }
                      else{ 
                         Class7.put(key, Math.log10((Class7.get(key))/Class1.get(splited[0]))); 
                      }
                }
                for (String key: Class8.keySet()){
                      String[] splited = key.split(" ");
                      if(splited[0].equals("<s>")){
                        Class8.put(key, Math.log10((Class8.get(key))/sentenceCount2));
                      }
                      else 
                         Class8.put(key, Math.log10((Class8.get(key))/Class2.get(splited[0]))); 
                }
                //Places log Probabilities of each word as values in each Category 
                for (String key: Class1.keySet()){
                    Class1.put(key, Math.log10((Class1.get(key))/count1)); 
                }
                for (String key: Class2.keySet()){
                    Class2.put(key, Math.log10((Class2.get(key))/count2)); 
                }
                
                //Naive Bayes Algorithm, where I just traverse through each doc and add probabilities of known words
                    Scanner scannerOut = new Scanner(new File(testPath));
                    double totProb1=0,totProb2=0,totProb7=0,totProb8=0;
                    String filePath;
                    double countString1=0, countString2=0, countString7=0, countString8=0, countString13=0, countString14=0, countString15=0, countString16=0;
                  //  System.out.println("Enter the absolute path for predictions file (No spaces at end)");
                  //  predictionsPath = path.nextLine();
                    predictionsPathU = "C://cygwin/home/skorpion/op_spam_v1.4/cv_p_fold3_predictions_u.labels";
                    File fileU = new File(predictionsPathU);
                    FileWriter fwU = new FileWriter(fileU.getAbsoluteFile());
                    BufferedWriter bwU = new BufferedWriter(fwU);
                    predictionsPathB = "C://cygwin/home/skorpion/op_spam_v1.4/cv_p_fold3_predictions_b.labels";
                    File fileB = new File(predictionsPathB);
                    FileWriter fwB = new FileWriter(fileB.getAbsoluteFile());
                    BufferedWriter bwB = new BufferedWriter(fwB);                    
                    while(scannerOut.hasNext()){
                        filePath=scannerOut.next();

                        ptbt = new PTBTokenizer(new FileReader(corpusPath+filePath),new CoreLabelTokenFactory(), "");
                        DocumentPreprocessor dp = new DocumentPreprocessor(corpusPath+filePath);
                        for (CoreLabel label; ptbt.hasNext(); ) {
                            label = (CoreLabel) ptbt.next();                    //reads 1 word in 
                            stringLabel = label.toString();
                            if(flagHashMap.containsKey(stringLabel)){       
                                continue;                          
                            }
                            flagHashMap.put(stringLabel, 1.0);
                            if(Class1.containsKey(stringLabel)){
                                countString1++;
                                totProb1 = totProb1+Class1.get(stringLabel);
                            }
                            else if(classMap.containsValue("a"))
                                countString2++;
                            if(Class2.containsKey(stringLabel)){
                                totProb2 = totProb2+Class2.get(stringLabel); 
                                countString7++;
                            }
                            else if(classMap.containsValue("b"))
                                countString8++;
                        }
                        for (List sentence : dp) {
                            for(int iter=0; iter<sentence.size(); iter++){
                                if(iter==0){
                                    bigramS="<s> " + sentence.get(0);
                                }
                                if(iter>0 && iter<(sentence.size()-1)){
                                    bigramS=sentence.get(iter-1)+" "+sentence.get(iter);
                                }
                                if((iter==(sentence.size()-1))&&sentence.size()>1){
                                    bigramS=sentence.get(sentence.size()-2)+" <s>";
                                }
                                if((iter==(sentence.size()-1))&& sentence.size()==1){
                                    bigramS=sentence.get(0)+" <s>";
                                }
                                if(Class7.containsKey(bigramS)){
                                    totProb7 = totProb7+Class7.get(bigramS);
                                   countString13++;
                                }
                                else
                                    countString14++;
                                if(Class8.containsKey(bigramS)){
                                    countString15++;
                                    totProb8=totProb8+Class8.get(bigramS);
                                }
                                else
                                    countString16++;
                            }  
                        }
                        double totalCount=count1+count2;
                  
                        //My smoothing algorithm, which instead of adding probabilities per unknown word, it multiplies
						//in the end by the ratio of unknown to known words to a power
                        //wordPer represent the percentage of words in a doc that were unknown to a given Category
                        //After the math, wordPer repserent the category probabilities
                        double wordPer1=countString2/countString1;
                        double wordPer4=countString8/countString7;
                        double wordPer7=countString14/countString13;
                        double wordPer8=countString16/countString15;
            
                        wordPer1 = Math.pow(wordPer1,.31)*(totProb1+Math.log10(count1/totalCount));   //unigram
                        wordPer4 = Math.pow(wordPer4,.3)*(totProb2+Math.log10(count2/totalCount));   //unigram
                        wordPer7 = Math.pow(wordPer7,1)*(totProb7+Math.log10(count1/totalCount));  //bigram       
                        wordPer8 = Math.pow(wordPer8,1)*(totProb8+Math.log10(count2/totalCount));  //bigram
                        double Max=0,maxBigram=0;
                        Max=Math.max(wordPer1,wordPer4);
                        maxBigram=Math.max(wordPer7,wordPer8);
                        if(maxBigram==wordPer7){
                              bwB.write(filePath+" "+findClass("a",classMap)+"\n");
                        }
                        if(maxBigram==wordPer8){
                            bwB.write(filePath+" "+findClass("b",classMap)+"\n");
                        }
                        
                        //Given a max probability, figures out which Category it is represented by
                        if(Max==wordPer1)
                            bwU.write(filePath+" "+findClass("a",classMap)+"\n");
                        if(Max==wordPer4)
                            bwU.write(filePath+" "+findClass("b",classMap)+"\n");
                        
                        //Zeroes all the counts and aall the percentages that were calculated, and clears the flagHashMap 
                        
                        countString1=0; countString2=0; countString7=0; countString8=0; 
                        countString13=0;countString14=0;countString15=0;countString16=0;
                        totProb1=0;totProb2=0;totProb7=0;totProb8=0;
                        flagHashMap.clear();
                    }
                    scannerOut.close();
                    path.close();
                    bwU.close();
                    bwB.close();
    }
//given the value of the classMap HSM, returns the String name of the Category
public static String findClass(String keyArray, HashMap<String,String> classMap){
    String holder = new String();
    for (String key: classMap.keySet()){
        if(classMap.get(key)==keyArray){
            holder = key;
        }
    }
    return holder;
}
}
