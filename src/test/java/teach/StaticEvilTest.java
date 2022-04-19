package teach;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Shows the problems of testing a class with static variables. Testing the two @Test methods
 * separately works OK. Executing the whole test driver results in assertion failures.
 *
 * @author Christoph Knabe
 * @since 2016-11-04
 */
public class StaticEvilTest {

  @Test
  public void count1time() {
    final Counter counter = new Counter();
    counter.incr();
    assertEquals(1, counter.get());
  }

  @Test
  public void count2times() {
    final Counter counter = new Counter();
    counter.incr();
    counter.incr();
    assertEquals(2, counter.get());
  }
}
