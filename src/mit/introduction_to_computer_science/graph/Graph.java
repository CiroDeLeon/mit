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
public class Graph extends Digraph {    
    @Override
    void addEdge(Edge edge) throws Exception {
        super.addEdge(edge); //To change body of generated methods, choose Tools | Templates.
        Edge rev=new Edge(edge.getDest(),edge.getSrc());
        super.addEdge(rev);
    }
}
