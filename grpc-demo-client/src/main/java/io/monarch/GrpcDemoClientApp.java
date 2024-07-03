package io.monarch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class GrpcDemoClientApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GrpcDemoClientApp.class,args);
    }
}
