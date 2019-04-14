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
	long initialTime = System.currentTimeMillis();
        Scanner in = new Scanner(arch);
        HashMap<String,Son> children = new HashMap<String,Son>();
        ArrayList<String> fathersName = new ArrayList<String>();
        HashMap<String,ArrayList<Son>> childrenList = new HashMap<String,ArrayList<Son>>();
        double lands = in.nextDouble();
        while(in.hasNext()){
            String father = in.next();
            String son = in.next();
            double sonLands = in.nextDouble();
            fathersName.add(father);
            Son s = new Son(son,father,sonLands);
            if(childrenList.containsKey(father))childrenList.get(father).add(s);
            else {
                childrenList.put(father,new ArrayList<Son>());
                childrenList.get(father).add(s);
            }
            children.put(son,s);
        }
        String treeGenerator = findTreeGenerator(fathersName,children);
        WarriorTree tree = new WarriorTree(children,fathersName,childrenList,treeGenerator,lands);
        tree.build(tree.getRoot());
        tree.buildLands(tree.getTreeGenerator());
        //tree.print(tree.getRoot());
        System.out.println(tree.getAnswer(tree.getRoot()));
	long finalTime = System.currentTimeMillis();
	double durationSeconds = (finalTime-initialTime)/1000.0;
	long durationMilliSeconds = finalTime-initialTime;
	System.out.println("Time: "+durationSeconds+"s"+"     or    "+durationMilliSeconds+"ms");
    }
    public static String findTreeGenerator(ArrayList<String> fathersName, HashMap<String,Son> children){
        for(String f:fathersName){
            if(children.get(f)==null)return f;
        }
        return null;        
    }
}
