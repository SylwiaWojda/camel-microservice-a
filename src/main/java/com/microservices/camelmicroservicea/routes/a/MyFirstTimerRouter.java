package com.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingComponent loggingComponent;
    @Override
    public void configure() throws Exception {
        from("timer:first-timer") //queue
                .log("${body}")
                .transform().constant("My Constant Message")
                .log("${body}")
//                .transform().constant("My Constant Message")
//                .transform().constant("Time now is" + LocalDateTime.now())
                .bean("getCurrentTimeBean")
                .log("${body}")
                .bean(loggingComponent)
                .log("${body}")
                .to("log:first-timer"); //database
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime() {
        return "Time now is" + LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {
    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);
    public void process(String message) {
        logger.info("SimpleLoggingProcessingComponent {}", message);
    }
}
