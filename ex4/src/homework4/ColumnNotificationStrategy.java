package homework4;

import java.util.ArrayList;
import java.util.List;

/**
 * ColumnNotificationStrategy creates an array of indices in the range [0,24]. The indices represent columns in a 5X5
 * billboard. The indices are sorted by columns, each column is sorted in increasing order.
 */
public class ColumnNotificationStrategy implements NotificationStrategy {
    /**
     * @effects creates an array of indices as explained in the class description.
     * @return the array of indices
     */
    @Override
    public List<Integer> getOrder() {
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
            {
                array.add(j*5 + i);
            }
        }

        return array;
    }
}
