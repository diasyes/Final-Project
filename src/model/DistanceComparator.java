package model;

import java.util.Comparator;

public class DistanceComparator implements Comparator<User>{

    @Override
    public int compare(User o1, User o2) {
        return Integer.compare(o1.getDistance(), o2.getDistance());
    }
}
