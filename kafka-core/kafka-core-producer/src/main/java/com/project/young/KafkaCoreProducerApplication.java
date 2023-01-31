package com.project.young;

import com.project.young.entity.Employee;
import com.project.young.producer.EmployeeJsonProducer2;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class KafkaCoreProducerApplication implements CommandLineRunner {

    private final EmployeeJsonProducer2 producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaCoreProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 5; i++) {
            Employee emp = new Employee("emp-"+i, "Employee"+i, LocalDate.now());
            producer.sendMessage(emp);
        }
    }
}
