package com.jrdtest.resttest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.jrdtest.resttest.entity.Transaction;
import com.jrdtest.resttest.repo.TransactionRepository;

@SpringBootApplication
@ComponentScan(basePackages = "com.*")
public class RestTestApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TransactionRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(RestTestApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {

        logger.info("Inserting Rec.", repository.save(new Transaction(1L, "test")));

        logger.info("Fetch Rec", repository.findOne(1L).toString());

        logger.info("Update 1L Rec.", repository.save(new Transaction(1L, "test-UPD")));

        logger.info("Fetch Rec. - UPD", repository.findOne(1L).toString());

        repository.delete(1L);
    }
}
