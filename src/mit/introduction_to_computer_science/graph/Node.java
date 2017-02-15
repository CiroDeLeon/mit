/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.graph;

/**
 *
 * @author Usuario1
 */
public class Node implements java.util.Comparator{
   private String name;    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Node n1=(Node) o1;
        Node n2=(Node) o2;
        if(n1.getName().equals(n2.getName())){
            return 0;
        }
        return 1;
    }
}