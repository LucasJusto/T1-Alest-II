import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;

public class Main{
    public static void main(String[] args)throws FileNotFoundException{
        Scanner user=new Scanner(System.in);
        System.out.println("Digite o diretorio do arquivo para extracao de dados(exemplo: casos/casoJB4a): ");
        File arch=new File(user.next());
        Scanner in = new Scanner(arch);
        HashMap<String,Son> children = new HashMap<String,Son>();
        HashMap<String,Father> fathers = new HashMap<String,Father>();
        ArrayList<String> fathersName = new ArrayList<String>();
        HashMap<String,ArrayList<Son>> childrenList = new HashMap<String,ArrayList<Son>>();
        double lands = in.nextDouble();
        while(in.hasNext()){
            String father = in.next();
            String son = in.next();
            double sonLands = in.nextDouble();
            fathersName.add(father);
            Son s = new Son(son,father,sonLands);
            Father f = new Father(father,son);
            if(fathers.containsKey(father))fathers.get(father).childrenNumber++;
            else fathers.put(father,f);
            if(childrenList.containsKey(father))childrenList.get(father).add(s);
            else {
                childrenList.put(father,new ArrayList<Son>());
                childrenList.get(father).add(s);
            }
            children.put(son,s);
        }
        Father treeGenerator = findTreeGenerator(fathersName,fathers,children);
        WarriorTree tree = new WarriorTree(fathers,children,fathersName,childrenList,treeGenerator.name,lands);
        tree.build(tree.getRoot());
        tree.buildLands(tree.getTreeGenerator());
        //tree.print(tree.getRoot());
        System.out.println(tree.getAnswer(tree.getRoot()));
    }
    public static Father findTreeGenerator(ArrayList<String> fathersName,HashMap<String,Father> fathers, HashMap<String,Son> children){
        for(String f:fathersName){
            if(children.get(f)==null)return fathers.get(f);
        }
        return null;        
    }
}