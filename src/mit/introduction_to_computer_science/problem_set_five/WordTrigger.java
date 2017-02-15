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
public class WordTrigger implements Trigger{
    private String word;
    @Override
    public boolean evaluate(NewsStory obj) {
        return true;
    }
    public boolean is_word_in(String text){
        if(text!=null){
        String[]words=text.split(" ");
        
        for(int i=0;i<words.length;i++){
            if(words[i].toLowerCase().equals(getWord().toLowerCase())==true){
                return true;
            }                
        }
        }
        return false;
    }

    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }
    
}
