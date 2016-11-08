package teach;

/**Manages a counter. As an example of bad (non-OO, non-unit-testable) programming style, it uses a static variable.
 * See http://stackoverflow.com/questions/7026507/why-are-static-variables-considered-evil
 * Created by knabe on 04.11.16.
 */
public class Counter {

    private static int count = 0;

    public void incr(){
        count++;
    }

    public int get(){
        return count;
    }

}
