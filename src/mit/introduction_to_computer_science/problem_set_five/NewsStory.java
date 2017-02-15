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
public class NewsStory {
 private String guid;
 private String title;
 private String subject;
 private String sumary;
 private String link;
 


    public NewsStory(String guid, String title, String subject, String sumary, String link) {
        this.guid = guid;
        this.title = title;
        this.subject = subject;
        this.sumary = sumary;
        this.link = link;
    }
    public NewsStory() {
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the sumary
     */
    public String getSumary() {
        return sumary;
    }

    /**
     * @param sumary the sumary to set
     */
    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }
}
