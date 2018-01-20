package homework4;

import java.util.ArrayList;
import java.util.List;

public class IncreasingNotificationStrategy implements NotificationStrategy {
    @Override
    public List<Integer> getOrder() {
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < 25; i++) {
            array.add(i);
        }

        return array;
    }
}
