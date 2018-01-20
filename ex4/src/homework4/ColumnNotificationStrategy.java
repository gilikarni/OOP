package homework4;

import java.util.ArrayList;
import java.util.List;

public class ColumnNotificationStrategy implements NotificationStrategy {
    @Override
    public List<Integer> getOrder() {
        ArrayList<Integer> array = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
            {
                array.add(i*5 + j);
            }
        }

        return array;
    }
}
