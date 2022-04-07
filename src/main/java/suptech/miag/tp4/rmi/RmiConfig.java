package suptech.miag.tp4.rmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
public class RmiConfig {

    private final ProductRemote remote;

    public RmiConfig(ProductRemote remote) {
        this.remote = remote;
    }

    @Bean
    public RemoteExporter registryRemote(){
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setServiceName("productRMI");
        exporter.setServiceInterface(ProductRemote.class);
        exporter.setService(remote);
        System.out.println(remote);
        return exporter;
    }

}
