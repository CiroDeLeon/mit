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
public class Edge {
    private Node src;
    private Node dest;
    private double weight;
    public Edge(Node src, Node dest) {
        this.src = src;
        this.dest = dest;
    }

    /**
     * @return the src
     */
    public Node getSrc() {
        return src;
    }

    /**
     * @param src the src to set
     */
    public void setSrc(Node src) {
        this.src = src;
    }

    /**
     * @return the dest
     */
    public Node getDest() {
        return dest;
    }

    /**
     * @param dest the dest to set
     */
    public void setDest(Node dest) {
        this.dest = dest;
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
