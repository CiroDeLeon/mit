/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_five;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Usuario1
 */
public class RSS_Reader implements Runnable{
   final URL url;
   Vector<NewsStory> list;
   Thread t;
   static final String TITLE = "title";
        static final String DESCRIPTION = "description";
        static final String CHANNEL = "channel";
        static final String LANGUAGE = "language";
        static final String COPYRIGHT = "copyright";
        static final String LINK = "link";
        static final String AUTHOR = "author";
        static final String ITEM = "item";
        static final String PUB_DATE = "pubDate";
        static final String GUID = "guid";

    public RSS_Reader(String url) {
        try {
           this.url = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        list=new Vector<NewsStory>();
        t=new Thread(this);
        t.start();
    }    
    public Vector<NewsStory> ReadNewsStrory() {
                list=new Vector<NewsStory>(); 
                NewsStory feed = null;
                try {
                        boolean isFeedHeader = true;
                        // Set header values intial to the empty string
                        String description = "";
                        String title = "";
                        String link = "";
                        String language = "";
                        String copyright = "";
                        String author = "";
                        String pubdate = "";
                        String guid = "";

                        // First create a new XMLInputFactory
                        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                        // Setup a new eventReader
                        InputStream in = read();
                        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                        // read the XML document
                        while (eventReader.hasNext()) {
                                XMLEvent event = eventReader.nextEvent();
                                if (event.isStartElement()) {
                                        String localPart = event.asStartElement().getName()
                                                        .getLocalPart();
                                        switch (localPart) {
                                        case ITEM:
                                                if (isFeedHeader) {
                                                        isFeedHeader = false;
                                                        feed = new mit.introduction_to_computer_science.problem_set_five.NewsStory(guid, title,author,description, link);
                                                }
                                                event = eventReader.nextEvent();
                                                break;
                                        case TITLE:
                                                title = getCharacterData(event, eventReader);
                                                break;
                                        case DESCRIPTION:
                                                description = getCharacterData(event, eventReader);
                                                break;
                                        case LINK:
                                                link = getCharacterData(event, eventReader);
                                                break;
                                        case GUID:
                                                guid = getCharacterData(event, eventReader);
                                                break;
                                        case LANGUAGE:
                                                language = getCharacterData(event, eventReader);
                                                break;
                                        case AUTHOR:
                                                author = getCharacterData(event, eventReader);
                                                break;
                                        case PUB_DATE:
                                                pubdate = getCharacterData(event, eventReader);
                                                break;
                                        case COPYRIGHT:
                                                copyright = getCharacterData(event, eventReader);
                                                break;
                                        }
                                } else if (event.isEndElement()) {
                                        if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                                                NewsStory message = new NewsStory();
                                                message.setTitle(title);
                                                message.setGuid(guid);
                                                message.setLink(link);
                                                message.setSumary(description);
                                                message.setSubject(copyright+" "+author);
                                                //System.out.println(author);
                                                event = eventReader.nextEvent();
                                                list.add(message);
                                                continue;
                                        }
                                }
                        }
                } catch (XMLStreamException e) {
                        throw new RuntimeException(e);
                }
                return list;
        }
    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)throws XMLStreamException {
                String result = "";
                event = eventReader.nextEvent();
                if (event instanceof Characters) {
                        result = event.asCharacters().getData();
                }
                return result;
        }
    private InputStream read() {
                try {
                        return url.openStream();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }
    public Vector<NewsStory> filter_stories(Vector<NewsStory> stories,Vector<Trigger> triggers){
        Vector<NewsStory> filter=new Vector<NewsStory>();
        Iterator<NewsStory> it=stories.iterator();        
        while(it.hasNext()){
            NewsStory news=it.next();
            Iterator<Trigger> it_=triggers.iterator();
            while(it_.hasNext()){
                Trigger t=it_.next();
                if(t.evaluate(news)){
                    filter.add(news);
                    break;
                }
            }
        }                
        return filter;
    }
    public static Vector<Trigger> readTriggerConfig(String file) throws FileNotFoundException, IOException{
       String curDir =System.getProperty("user.dir");
       java.io.File f=new java.io.File(curDir+File.separator+file);
       java.io.FileReader fr=new java.io.FileReader(f);
       java.io.BufferedReader br=new java.io.BufferedReader(fr);
       String line="";
       String[] names=new String[100];
       String[] types=new String[100];
       String[] words=new String[100];
       String[] phrases=new String[100];
       String[] p=new String[100];
       String[] q=new String[100];
       Trigger[]t=new Trigger[100];
       Vector<Trigger> set=new Vector<Trigger>();
       int c=0;
       while((line=br.readLine())!=null){
          if(line.charAt(0)!='#'){
             String []palabras=line.split(" ");
             if(palabras[0].equals("ADD")==false){
                 names[c]=palabras[0];                 
                 types[c]=palabras[1];
                 words[c]=palabras[2];
                 phrases[c]="";                 
                 for(int i=2;i<palabras.length;i++){
                     phrases[c]+=palabras[i];
                 }
                 p[c]=palabras[2];
                 if(palabras.length>3){
                    q[c]=palabras[3];
                 }else{
                    q[c]=""; 
                 }
                 
                 if(types[c].equals("TITLE")){
                     TitleTrigger tt=new TitleTrigger(words[c]);
                     t[c]=tt;
                 }
                 if(types[c].equals("SUBJECT")){
                     SubjectTrigger tt=new SubjectTrigger(words[c]);
                     t[c]=tt;
                 }
                 if(types[c].equals("SUMARY")){
                     SumaryTrigger tt=new SumaryTrigger(words[c]);
                     t[c]=tt;
                 }
                 if(types[c].equals("PHRASE")){
                     PhraseTrigger tt=new PhraseTrigger(phrases[c]);
                     t[c]=tt;
                 }
                 if(types[c].equals("NOT")){
                     int pos=0;
                     for(int i=0;i<c;i++){
                         if(names[i].equals(p[i])){
                             pos=i;
                             break;
                         }
                     }
                     NotTrigger tt=new NotTrigger(t[pos]);
                 }
                 if(types[c].equals("AND")){
                     int pos_p=0;
                     int pos_q=0;
                     for(int i=0;i<c;i++){
                         if(names[i].equals(p[i])){
                             pos_p=i;                             
                         }
                         if(names[i].equals(q[i])){
                             pos_q=i;
                         }
                     }
                     AndTrigger tt=new AndTrigger(t[pos_p],t[pos_q]);
                     t[c]=tt;
                 }
                 if(types[c].equals("OR")){
                     int pos_p=0;
                     int pos_q=0;
                     for(int i=0;i<c;i++){
                         if(names[i].equals(p[i])){
                             pos_p=i;                             
                         }
                         if(names[i].equals(q[i])){
                             pos_q=i;
                         }
                     }
                     OrTrigger tt=new OrTrigger(t[pos_p],t[pos_q]);
                     t[c]=tt;
                 }
                 c++;                 
             }else{
                 for(int i=1;i<palabras.length;i++){
                     for(int j=0;j<c;j++){
                         if(names[j].equals(palabras[i])){
                             set.add(t[j]);
                         }
                     }
                 }
             }
          }    
       }
       return set;        
    }
    @Override
    public void run() {
       while(true){
           try {      
               Vector<Trigger> l=RSS_Reader.readTriggerConfig("triggers.txt"); 
               Iterator<NewsStory> it=this.filter_stories(this.ReadNewsStrory(),l).iterator();               
               
               while(it.hasNext()){
                  NewsStory obj=it.next();
                  System.out.println(obj.getTitle());    
                  System.out.println(obj.getSumary());
                  System.out.println(obj.getLink());                  
               }
               
               
               Thread.sleep(60000);
           } catch (InterruptedException ex) {
               Logger.getLogger(RSS_Reader.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(RSS_Reader.class.getName()).log(Level.SEVERE, null, ex);
           }
       } 
    }
    
}
