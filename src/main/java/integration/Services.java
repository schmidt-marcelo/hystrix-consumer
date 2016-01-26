package integration;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mschmidt on 1/20/16.
 */
public class Services {

    public Services() {
    }

    public ServiceStatus status(String serviceName) {
        try {
            return getStatus(serviceName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ServiceStatus getStatus(String serviceName) throws IOException {
        HttpClient client = HttpClients.createDefault();
        String url = String.format("http://192.168.99.100:8880/hystrix-exploration-1.0/services/status/%s", serviceName);
        HttpGet get = new HttpGet(url);
        HttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Sem sucesso.");
        }
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(response.getEntity().getContent()), ServiceStatus.class);
    }
}
