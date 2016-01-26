package integration;

/**
 * Created by mschmidt on 1/20/16.
 */
public class ServiceStatus {

    private final String serviceName;
    private final String status;

    public ServiceStatus(String serviceName, String status) {
        this.serviceName = serviceName;
        this.status = status;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getStatus() {
        return status;
    }
}
