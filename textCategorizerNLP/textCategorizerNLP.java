package textcategorizernlp;
/* 
Sheryan Resutov
Natural Language Processing
Professor Sable
Project I
3/23/2014
*/
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.ling.HasWord;
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

public class TextCategorizerNLP {

    public static void main(String args[]) throws IOException {
        //Hashmaps for each of the 2-6 Categories
        HashMap<String, Double> Class1 = new HashMap();
        HashMap<String, Double> Class4 = new HashMap();
        HashMap<String, Double> Class3 = new HashMap();
        HashMap<String, Double> Class2 = new HashMap();
        HashMap<String, Double> Class5 = new HashMap();
        HashMap<String, Double> Class6 = new HashMap();
        HashMap<String, Double> Class7 = new HashMap();
        HashMap<String, Double> Class8 = new HashMap();
        HashMap<String, String> classMap = new HashMap();       //Stores current Categories
        HashMap<String, Double> flagHashMap = new HashMap();    //Stores words that already appeared in a doc
        String stringLabel;                                     //Stores the current word in a document
        String className;                                       //Stores the current Category name
        double count1=0.0,count2=0.0,count3=0.0,count4=0.0,count5=0.0, count6=0;        //Count of # of Docs in each Category
        int i=0; 
        String trainPath, testPath, corpusPath, predictionsPath,bigramS;
        String[] keyArray = new String[]{"a","b","c","d","e","f"};                      //used later to access Category names in the classMap HSM
                Scanner path = new Scanner(System.in);
             //   System.out.println("Enter the absolute path for training file (No spaces at the end)");
             //   trainPath = path.nextLine();
                trainPath = "C://cygwin/home/skorpion/op_spam_v1.4/cv_fold2_train.labels";
             //   System.out.println("Enter the absolute path for testing file (No spaces at the end)");
             //   testPath = path.nextLine();
                testPath = "C://cygwin/home/skorpion/op_spam_v1.4/cv_fold2_test.list";
              //  System.out.println("Enter the absolute path for the corpuses (ie. C://TC_provided/ for us)(No spaces at the end)");
              //  corpusPath = path.nextLine();
                corpusPath = "C://cygwin/home/skorpion/op_spam_v1.4/";
                Scanner scannerIn = new Scanner(new File(trainPath));
                PTBTokenizer ptbt;
                            DocumentPreprocessor dp = new DocumentPreprocessor("C://cygwin/home/skorpion/op_spam_v1.4/negative_polarity/deceptive_from_MTurk/fold1/d_hilton_17.txt");
                            for (List sentence : dp) {
                                for(int iter=0; iter<sentence.size(); iter++){
                                    if(iter==0){
                                        System.out.println("<s> " + sentence.get(0));
                                        bigramS="<s> " + sentence.get(0);
                                    }
                                    if(iter>0 && iter<(sentence.size()-1)){
                                        System.out.println(sentence.get(iter-1)+" "+sentence.get(iter));
                                        bigramS=sentence.get(iter-1)+" "+sentence.get(iter);
                                    }
                                    if(iter==(sentence.size()-1)){
                                        System.out.println(sentence.get(sentence.size()-2)+" <s> ");
                                        bigramS=sentence.get(sentence.size()-2)+" <s>";
                                    }

                                }
                            }
                while(scannerIn.hasNext()){
                    try {
                        ptbt = new PTBTokenizer(new FileReader(corpusPath+"/"+scannerIn.next()),new CoreLabelTokenFactory(), "");
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
                                flagHashMap.clear(); 
                                break;
                            case "c":   
                                count3++;
                                for (CoreLabel label; ptbt.hasNext(); ) {
                                    label = (CoreLabel) ptbt.next();
                                    stringLabel = label.toString();
                                    if(Class3.containsKey(stringLabel) && (!flagHashMap.containsKey(stringLabel))){
                                        Class3.put(stringLabel, Class3.get(stringLabel)+1);
                                        flagHashMap.put(stringLabel,1.0);
                                    } 
                                    if(!Class3.containsKey(stringLabel)){
                                        Class3.put(stringLabel,1.0);
                                        flagHashMap.put(stringLabel,1.0);
                                    }                  
                                }
                                flagHashMap.clear(); 
                                break;
                            case "d":   
                                count4++;
                                for (CoreLabel label; ptbt.hasNext(); ) {
                                    label = (CoreLabel) ptbt.next();
                                    stringLabel = label.toString();
                                    if(Class4.containsKey(stringLabel) && (!flagHashMap.containsKey(stringLabel))){
                                        Class4.put(stringLabel, Class4.get(stringLabel)+1);
                                        flagHashMap.put(stringLabel,1.0);
                                    } 
                                    if(!Class4.containsKey(stringLabel)){
                                        flagHashMap.put(stringLabel,1.0);
                                        Class4.put(stringLabel,1.0);
                                    }                  
                                }
                                flagHashMap.clear(); 
                                break;
                            case "e":  
                                count5++;
                                for (CoreLabel label; ptbt.hasNext(); ) {
                                    label = (CoreLabel) ptbt.next();
                                    stringLabel = label.toString();
                                    if(Class5.containsKey(stringLabel) && (!flagHashMap.containsKey(stringLabel))){
                                        Class5.put(stringLabel, Class5.get(stringLabel)+1);
                                        flagHashMap.put(stringLabel,1.0);
                                    } 
                                    if(!Class5.containsKey(stringLabel)){
                                        Class5.put(stringLabel,1.0);
                                        flagHashMap.put(stringLabel,1.0);
                                    }                  
                                }
                                flagHashMap.clear(); 
                                break;
                            case "f":   
                                count6++;
                                for (CoreLabel label; ptbt.hasNext(); ) {
                                    label = (CoreLabel) ptbt.next();
                                    stringLabel = label.toString();
                                    if(Class6.containsKey(stringLabel) && (!flagHashMap.containsKey(stringLabel))){
                                        Class6.put(stringLabel, Class6.get(stringLabel)+1);
                                        flagHashMap.put(stringLabel,1.0);
                                    } 
                                    if(!Class6.containsKey(stringLabel)){
                                        Class6.put(stringLabel,1.0);
                                        flagHashMap.put(stringLabel,1.0);
                                    }                  
                                }
                                flagHashMap.clear(); 
                                break;
                        }
                    }   
                    catch (FileNotFoundException ex) {
                        Logger.getLogger(TextCategorizerNLP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }           
                scannerIn.close();

                //Places log Probabilities of each word as values in each Category 
                for (String key: Class1.keySet()){
                    Class1.put(key, Math.log10((Class1.get(key))/count1)); 
                }
                for (String key: Class2.keySet()){
                    Class2.put(key, Math.log10((Class2.get(key))/count2)); 
                }
                for (String key: Class3.keySet()){
                    Class3.put(key, Math.log10((Class3.get(key))/count3)); 
                }
                for (String key: Class4.keySet()){
                    Class4.put(key, Math.log10((Class4.get(key))/count4)); 
                }
                for (String key: Class5.keySet()){
                    Class5.put(key, Math.log10((Class5.get(key))/count5)); 
                }
                for (String key: Class6.keySet()){
                    Class6.put(key, Math.log10((Class6.get(key))/count6)); 
                }
                
                //Naive Bayes Algorithm, where I just traverse through each doc and add probabilities of known words
                    Scanner scannerOut = new Scanner(new File(testPath));
                    double totProb1=0,totProb2=0,totProb3=0,totProb4=0,totProb5=0,totProb6=0;
                    String filePath;
                    double countString1=0, countString2=0, countString3=0, countString4=0, countString5=0, countString6=0, 
                           countString7=0, countString8=0, countString9=0, countString10=0,countString11=0,countString12=0;
                  //  System.out.println("Enter the absolute path for predictions file (No spaces at end)");
                  //  predictionsPath = path.nextLine();
                    predictionsPath = "C://cygwin/home/skorpion/op_spam_v1.4/cv_fold2_predictions.labels";
                    File file = new File(predictionsPath);
                    FileWriter fw = new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    while(scannerOut.hasNext()){
                        filePath=scannerOut.next();

                        ptbt = new PTBTokenizer(new FileReader(corpusPath+filePath),new CoreLabelTokenFactory(), "");
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
                            if(Class4.containsKey(stringLabel)){
                                totProb4 = totProb4+Class4.get(stringLabel); 
                                countString3++;
                            }
                            else if(classMap.containsValue("d"))
                                countString4++;
                            if(Class3.containsKey(stringLabel)){
                                totProb3 = totProb3+Class3.get(stringLabel); 
                                countString5++;
                            }
                            else if(classMap.containsValue("c"))
                                countString6++;
                            if(Class2.containsKey(stringLabel)){
                                totProb2 = totProb2+Class2.get(stringLabel); 
                                countString7++;
                            }
                            else if(classMap.containsValue("b"))
                                countString8++;
                            if(Class5.containsKey(stringLabel)){
                                totProb5 = totProb5+Class5.get(stringLabel); 
                                countString9++;
                            }
                            else if(classMap.containsValue("e"))
                                countString10++;
                            if(Class6.containsKey(stringLabel)){
                                totProb6 = totProb6+Class6.get(stringLabel); 
                                countString11++;
                            }
                            else if(classMap.containsValue("f"))
                                countString12++;
                        }
                        
                        //My smoothing algorithm, which instead of adding probabilities per unknown word, it multiplies
						//in the end by the ratio of unknown to known words to a power
                        //wordPer represent the percentage of words in a doc that were unknown to a given Category
                        //After the math, wordPer repserent the category probabilities
                        double wordPer1=countString2/countString1;
                        double wordPer2=countString4/countString3;
                        double wordPer3=countString6/countString5;
                        double wordPer4=countString8/countString7;
                        double wordPer5=countString10/countString9;
                        double wordPer6=countString12/countString11;
                        double totalCount=count1+count2+count3+count4+count5+count6;
                        wordPer1 = Math.pow(wordPer1,smoothFactor("a",classMap))*(totProb1+Math.log10(count1/totalCount));  
                        wordPer4 = Math.pow(wordPer4,smoothFactor("b",classMap))*(totProb2+Math.log10(count2/totalCount));   
                        if(classMap.size()>2){
                            wordPer2 = Math.pow(wordPer2,smoothFactor("d",classMap))*(totProb4+Math.log10(count4/totalCount));   
                            wordPer3 = Math.pow(wordPer3,smoothFactor("c",classMap))*(totProb3+Math.log10(count3/totalCount));   
                            wordPer5 = Math.pow(wordPer5,smoothFactor("e",classMap))*(totProb5+Math.log10(count5/totalCount));   
                        }
                        if(classMap.size()>5)
                            wordPer6 = Math.pow(wordPer6,smoothFactor("f",classMap))*(totProb6+Math.log10(count6/totalCount));	
                        
                        //Figures out Max Probability out of all Categories for each Corpus
                        double Max=0;
                        if(classMap.size()==6)
                            Max = Math.max(wordPer6,Math.max(wordPer5,Math.max(wordPer4,Math.max(wordPer3,Math.max(wordPer2,wordPer1)))));
                        if(classMap.size()==5) 
                            Max = Math.max(wordPer5,Math.max(wordPer4,Math.max(wordPer3,Math.max(wordPer2,wordPer1))));
                        if(classMap.size()==2)
                            Max=Math.max(wordPer1,wordPer4);
                        
                        //Given a max probability, figures out which Category it is represented by
                        if(Max==wordPer1)
                            bw.write(filePath+" "+findClass("a",classMap)+"\n");
                        if(Max==wordPer2)
                            bw.write(filePath+" "+findClass("d",classMap)+"\n");
                        if(Max==wordPer3)
                            bw.write(filePath+" "+findClass("c",classMap)+"\n");
                        if(Max==wordPer4)
                            bw.write(filePath+" "+findClass("b",classMap)+"\n");
                        if(Max==wordPer5)
                            bw.write(filePath+" "+findClass("e",classMap)+"\n");
                        if(Max==wordPer6)
                            bw.write(filePath+" "+findClass("f",classMap)+"\n");
                        
                        //Zeroes all the counts and aall the percentages that were calculated, and clears the flagHashMap 
                        
                        countString1=0; countString2=0; countString3=0; countString4=0; countString5=0; countString6=0; 
                        countString7=0; countString8=0; countString9=0; countString10=0; countString11=0; countString12=0;
                        totProb1=0;totProb2=0;totProb3=0;totProb4=0;totProb5=0;totProb6=0;
                        flagHashMap.clear();
                    }
                    scannerOut.close();
                    path.close();
                    bw.close();
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
//Returns the smoothing factor that i determined earlier through testing and looking at Recall/Precision for each Category
public static Double smoothFactor(String keyArray, HashMap<String,String> classMap){
    HashMap<String, Double> SmoothingNums = new HashMap();
    SmoothingNums.put("Cri", 0.62);
    SmoothingNums.put("Pol", 0.52);
    SmoothingNums.put("Dis", 0.55);
    SmoothingNums.put("Str", 0.52);
    SmoothingNums.put("Oth", 0.55);
    SmoothingNums.put("I", 1.21);
    SmoothingNums.put("O", 1.15);
    SmoothingNums.put("USN", 0.7);
    SmoothingNums.put("Wor", 0.67);
    SmoothingNums.put("Fin", 1.01);
    SmoothingNums.put("Sci", 1.00);
    SmoothingNums.put("Spo", 1.00);
    SmoothingNums.put("Ent", 0.97);
    SmoothingNums.put("D", 0.3);   //.23 or .28
    SmoothingNums.put("T", 0.3);   //.23 or .28
    return SmoothingNums.get(findClass(keyArray,classMap));

}
}
