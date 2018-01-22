package homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomNotificationStrategy creates an array of indices in the range [0,24] in  random order.
 */
public class RandomNotificationStrategy implements NotificationStrategy {
    /**
     * @effects creates an array of indices in the range [0,24] in  random order.
     * @return the array of indices
     */
    @Override
    public List<Integer> getOrder() {
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> indexArray = (ArrayList<Integer>) new IncreasingNotificationStrategy().getOrder();
        Random randomNumberGenerator = new Random();

        for (int i = 0; i < 25; i++) {
            int index = randomNumberGenerator.nextInt(indexArray.size());
            array.add(indexArray.get(index));
            indexArray.remove(index);
        }

        return array;
    }
}
