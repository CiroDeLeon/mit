/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mit.introduction_to_computer_science.graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Usuario1
 */
public class Digraph {
    Set<Node> nodes;
    Map<Node,Set<Edge>> edges;    
    public Digraph() {
        nodes=new HashSet<Node>();
        edges=new HashMap<Node,Set<Edge>>();
    }
    void addNode(Node node) throws Exception{
        if(Digraph.Exist(node,nodes)){
            throw new Exception("Duplicate Node");
        }else{
            nodes.add(node);
            edges.put(node,new HashSet<Edge>());
        }
    }
    void addEdge(Edge edge) throws Exception{
        Node source=edge.getSrc();
        Node dest=edge.getDest();
        if(!Digraph.Exist(source,nodes) && !Digraph.Exist(dest,nodes)){
            throw new Exception("Node not in graph");
        }
    }
    public Set<Edge> childrenOf(Node node){
        return this.edges.get(node);
    }
    public boolean hasNode(Node node){
        return Digraph.Exist(node,nodes);
    }
    public static boolean Exist(Node node,Set<Node> nodes){
        Iterator<Node> it=nodes.iterator();
        while(it.hasNext()){
            Node item=it.next();
            if(item.getName().equals(node.getName())){
                return true;
            }
        }
        return false;
    }
}
