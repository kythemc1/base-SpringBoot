package co.siten.osp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@SpringBootApplication(scanBasePackages = { "co.siten.osp" })
@Controller
@EnableZuulProxy
@EnableDiscoveryClient
@PropertySource(value = { "classpath:application.properties" })
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
