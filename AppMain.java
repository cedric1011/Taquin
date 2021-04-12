import java.util.Comparator;
import java.util.HashMap;
import  java.util.PriorityQueue;
public class AppMain implements Comparator<String>{

    public static void main(String[] args) {
        PriorityQueue file = new PriorityQueue();

        HashMap verifCase = new HashMap();
        verifCase.put(1, "0:1");
        verifCase.put(2, "195:1");
        verifCase.put(3, "390:1");
        verifCase.put(4, "0:188");
        verifCase.put(5, "195:188");
        verifCase.put(6, "390:188");
        verifCase.put(7, "0:395");
        verifCase.put(8, "195:395");

        Board frame = new Board(verifCase);
    }

    public int compare(String a, String b) {

        return 0;
    }
}