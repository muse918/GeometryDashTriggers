package GeometryDashTriggers;

public class CountInteger {
    public int countInt;
    public CountInteger(int x){
        countInt = x;
    }
    public synchronized void add(int x){
        countInt+=x;
    }
}
