package com.example.kafka_binder_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class KafkaBinderDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaBinderDemoApplication.class, args);
	}


	@Bean
	public Supplier<String> producerBinding(){
		return () -> {
			try{
				Thread.sleep(1500);
			} catch (InterruptedException e){
				throw new RuntimeException(e);
			}
			return "new data";
		};
	}

	// Processor will fetch data from one topic perfom its logic and then send new topic
	@Bean
	public Function<String, String> processorBinding(){
		return s -> s +" :: "+ System.currentTimeMillis();
	}

	@Bean
	public Consumer<String> consumerBinding(){
		return s -> System.out.println("Data consumed :: " + s.toUpperCase());
	}
}
