package homework4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNotificationStrategy implements NotificationStrategy {
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