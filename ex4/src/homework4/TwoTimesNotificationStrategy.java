package homework4;

import java.util.ArrayList;
import java.util.List;

/**
 * TwoTimesNotificationStrategy creates an array of indices in the range [0,24]. The array contains a concatenation of
 * the evan indices sorted in increasing order and than the odd indices sorted in increasing order.
 */
public class TwoTimesNotificationStrategy implements NotificationStrategy {
    /**
     * @effects create an array of indices as explained in the class description.
     * @return the array of indices
     */
    @Override
    public List<Integer> getOrder() {
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            for (int j = i; j < 25; j += 2)
            {
                array.add(j);
            }
        }

        return array;
    }
}
