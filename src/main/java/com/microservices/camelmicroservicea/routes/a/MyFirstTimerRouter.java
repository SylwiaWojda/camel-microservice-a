package com.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:first-timer") //queue
//                .transform().constant("My Constant Message")
                .transform().constant("Time now is" + LocalDateTime.now())
                .to("log:first-timer"); //database
    }
}
