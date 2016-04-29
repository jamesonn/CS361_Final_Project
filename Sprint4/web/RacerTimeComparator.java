import java.util.Comparator;

/**
 * Created by Nate on 4/29/2016.
 */
public class RacerTimeComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1,Object o2){
        if(o1 instanceof Racer && o2 instanceof Racer){
            Racer r1 = (Racer) o1;
            Racer r2 = (Racer) o2;
            if(r1.getTotalTime() < r2.getTotalTime()){
                return -1;
            }else if(r1.getTotalTime() > r2.getTotalTime()){
                return 1;
            }
        }
        return 0;
    }
}
