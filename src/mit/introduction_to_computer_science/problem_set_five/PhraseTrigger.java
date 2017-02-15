/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.problem_set_five;

/**
 *
 * @author Usuario1
 */
public class PhraseTrigger implements Trigger{
    String phrase;        
    
    public PhraseTrigger(String phrase) {
        this.phrase = phrase;        
    }
    @Override
    public boolean evaluate(NewsStory obj) {        
        return this.is_phrase_in(obj.getTitle()) || this.is_phrase_in(obj.getSubject()) || this.is_phrase_in(obj.getSumary());
    }    
    public boolean is_phrase_in(String text){
        if(text!=null){
           return text.contains(phrase);
        }else{
           return false; 
        }
    }
}
