package homework4;

import java.util.List;

/**
 * Creates an array of indices in the range [0,24]
 */
interface NotificationStrategy {
    /**
     * @effects creates a new index array from the range [0,24]
     * @return the array of indices
     */
    abstract public List<Integer> getOrder();
}