import java.util.Comparator;

/**
 * This is unfinished
 */
public class RacerPlaceComparator implements Comparator<Object> {

    @Override
    public int compare(Object o1,Object o2){
        if(o1 instanceof Racer && o2 instanceof Racer){
            Racer r1 = (Racer) o1;
            Racer r2 = (Racer) o2;
            return r1.getBibNum() - r2.getBibNum();
        }
        return 0;
    }
}
