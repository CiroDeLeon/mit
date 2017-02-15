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
public class NotTrigger implements Trigger{
    Trigger trigger;   
    public NotTrigger(Trigger trigger) {
        this.trigger = trigger;
    }
    @Override
    public boolean evaluate(NewsStory obj) {
        return !trigger.evaluate(obj);
    }
}
