package homework4;

import java.util.ArrayList;
import java.util.List;

public class TwoTimesNotificationStrategy implements NotificationStrategy {
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
