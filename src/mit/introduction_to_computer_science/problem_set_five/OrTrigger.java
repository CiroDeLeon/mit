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
public class OrTrigger implements Trigger{
    Trigger t1;
    Trigger t2;    
    public OrTrigger(Trigger t1, Trigger t2) {
        this.t1 = t1;
        this.t2 = t2;
    }
    @Override
    public boolean evaluate(NewsStory obj) {
        return t1.evaluate(obj) || t2.evaluate(obj);
    }

}
