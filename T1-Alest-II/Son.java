public class Son{
    public String name, father;
    double lands;
    public Son(String s,  String f, double l){
        name=s;
        father=f;
        lands=l;
    }
    @Override
    public String toString(){
        return name+"   "+lands;
    }
}