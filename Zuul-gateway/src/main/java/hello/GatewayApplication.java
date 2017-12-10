package hello;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@EnableZuulProxy
@SpringBootApplication
@Controller
public class GatewayApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(GatewayApplication.class).web(true).run(args);
  }



}
