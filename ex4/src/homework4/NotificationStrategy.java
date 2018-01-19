package homework4;

import java.awt.*;
import java.util.List;

interface NotificationStrategy {
    abstract public void notifyAll(List<Panel> panels, Color color);
}
