import java.util.ArrayList;
import java.util.HashMap;

public class WarriorTree{
    private Node root;
    private HashMap<String,Son> children;
    private ArrayList<String> fathersName;
    private HashMap<String,ArrayList<Son>> childrenList;
    private Node res;
    private double higher;
    private class Node{
        public String name;
        public double lands;
        public ArrayList<Node> children;
        public Node(String n, double l){
            name=n;
            lands=l;
            children = new ArrayList<Node>();
        }
        @Override
        public String toString(){
            return name+"   "+lands;
        }     
    }
    public WarriorTree(HashMap<String,Son> children,ArrayList<String>fathersName,HashMap<String,ArrayList<Son>> childrenList,String f, double lands){
        this.children=children;
        this.fathersName=fathersName;
        this.childrenList=childrenList;
        root=new Node(null,0);
        root.children.add(new Node(f,lands));
    }
    public void build(Node n){
        for(Node a:n.children){
            if(childrenList.get(a.name)!=null){
                a.children=convertToNodeList(childrenList.get(a.name));
            }
            build(a);
        }
    }
    private ArrayList<Node> convertToNodeList(ArrayList<Son> aux){
        ArrayList<Node> res = new ArrayList<Node>();
        for(Son s: aux){
            res.add(new Node(s.name,s.lands));
        }
        return res;
    }
    public Node getRoot(){return this.root;}
    public void print(Node r){
        if(r.name!=null)System.out.println(r.name+"   "+r.lands);
        for(Node n:r.children){
            print(n);
        }
    }
    public void buildLands(Node f){
        for(Node n:f.children){
            n.lands+= f.lands/childrenList.get(f.name).size();
            buildLands(n);
        }
    }
    public Node getTreeGenerator(){
        return this.root.children.get(0);
    }
    public Node getAnswer(Node r){
        for(Node n:r.children){
            if(n.children.size()==0){
                if(n.lands>higher){
                    higher=n.lands;
                    res=n;
                }
            }
            getAnswer(n);
        }
        return res;
    }
}
