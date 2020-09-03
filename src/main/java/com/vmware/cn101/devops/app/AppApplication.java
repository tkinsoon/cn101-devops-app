package com.vmware.cn101.devops.app;

import io.prometheus.client.exporter.HTTPServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("CN101 - Hello World");
        HTTPServer server = new HTTPServer(8181);
    }
}
