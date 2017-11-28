import java.awt.*;

/**
 * An ImpossibleSizeException is an exception thrown when an impossible size was given as argument to a function or
 * method. The exception holds an alternative size for the function or method to use, obtainable by the
 * getCorrectSize public method.
 */
public class ImpossibleSizeException extends Exception{
    static final Dimension correctSize = new Dimension(100,100);

    /**
     * @return an Object holding the alternative correct size.
     */
    public Dimension getCorrectSize() {
        return correctSize;
    }
}
