package infra;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import integration.ServiceStatus;
import integration.Services;


/**
 * Created by mschmidt on 1/21/16.
 */
public class ServicesClient extends HystrixCommand<ServiceStatus> {

    private final Services services;
    private final String serviceName;

    public ServicesClient(Services services, String serviceName) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ServicesClient")).
                        andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerEnabled(true)
                                        .withCircuitBreakerErrorThresholdPercentage(30)));
        this.services = services;
        this.serviceName = serviceName;
    }
    @Override
    protected ServiceStatus run() throws Exception {
        return services.status(serviceName);
    }

    @Override protected ServiceStatus getFallback() {
        return new ServiceStatus(this.serviceName, "Is down");
    }
}
