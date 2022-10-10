package com.microservices.camelmicroservicea.routes.c;

import org.apache.camel.builder.RouteBuilder;

public class ActiveMqSenderRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:active-mq-timer?period=10000")
                .transform().constant("My message for Active MQ")
                .log("${body}")
                .to("activemq:my-activemq-queue");
    }



}
