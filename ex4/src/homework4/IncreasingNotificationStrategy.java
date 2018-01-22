package homework4;

import java.util.ArrayList;
import java.util.List;

/**
 * IncreasingNotificationStrategy creates an array of indices in increasing order from the range [0,24]
 */
public class IncreasingNotificationStrategy implements NotificationStrategy {
    /**
     * @effects create an array with sorted indices from the range [0,24]
     */
    @Override
    public List<Integer> getOrder() {
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            array.add(i);
        }

        return array;
    }
}
