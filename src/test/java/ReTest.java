import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.junit.Test;

/**
 * Created by mschmidt on 1/28/16.
 */
public class ReTest {

    @Test
    public void testTimeouts() {
        TestCommand test = new TestCommand(
                HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ReTest"))
                    .andCommandPropertiesDefaults(
                            HystrixCommandProperties.Setter()
                                    .withExecutionTimeoutEnabled(true)
                                    .withExecutionTimeoutInMilliseconds(1000)
                                    .withCircuitBreakerEnabled(true)
                                    .withCircuitBreakerErrorThresholdPercentage(30)));
        System.out.println("Result == " + test.execute());
    }

    static class TestCommand extends HystrixCommand<String> {

        protected TestCommand(Setter setter) {
            super(setter);
        }

        @Override
        protected String run() throws Exception {
            System.out.println("running... ... ...: " + System.currentTimeMillis());
            Thread.sleep(2000);
            return "Timed out ... ... ...: " + System.currentTimeMillis();
        }

        @Override
        protected String getFallback() {
            System.out.println("fallback start... ... ...: " + System.currentTimeMillis());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted... ... ");
                e.printStackTrace();
            }
            return "Fallback end... ... ...: " + System.currentTimeMillis();
        }
    }
}
