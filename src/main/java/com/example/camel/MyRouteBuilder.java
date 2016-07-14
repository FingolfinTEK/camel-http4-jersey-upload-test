package com.example.camel;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.springframework.stereotype.Component;

import static org.apache.http.entity.ContentType.MULTIPART_FORM_DATA;

/**
 * A Camel Java DSL Router
 */
@Component
public class MyRouteBuilder extends RouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java code...
     */
    public void configure() {
        from("file:src/data?delay=5000")
            .process(new Processor() {
                public void process(Exchange exchange) throws Exception {
                    StringBody username =
                        new StringBody("username", MULTIPART_FORM_DATA);
                    StringBody password =
                        new StringBody("password", MULTIPART_FORM_DATA);

                    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
                    multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    multipartEntityBuilder.addPart("username", username);
                    multipartEntityBuilder.addPart("password", password);

                    String filename = (String) exchange.getIn().getHeader(Exchange.FILE_NAME);
                    File file = exchange.getIn().getBody(File.class);
                    multipartEntityBuilder.addPart("upload",
                        new FileBody(file, MULTIPART_FORM_DATA, filename));

                    exchange.getIn().setBody(multipartEntityBuilder.build());
                }
            })
            .to("http4://localhost:8080/restwb/upload");
    }

}
