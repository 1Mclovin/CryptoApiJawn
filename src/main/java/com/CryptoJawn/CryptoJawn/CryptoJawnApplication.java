package com.CryptoJawn.CryptoJawn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.Arrays;

@EnableScheduling
@SpringBootApplication
public class CryptoJawnApplication {

	//private static final Logger log = LoggerFactory.getLogger(CryptoJawnApplication.class);
	private static final Logger log = LoggerFactory.getLogger(CryptoJawnApplication.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");



	public static void main(String[] args) {
		SpringApplication.run(CryptoJawnApplication.class, args);

	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			//this had to be an array because everything is inside of an array in the api json
			threeSecondReset();
		};
	}

	@Scheduled(fixedRate = 3000)
	public static void threeSecondReset(){
		RestTemplate restTemplate = new RestTemplate();
		TheOnes [] theones = restTemplate.getForObject(
				"https://api.n.exchange/en/api/v1/price/BTCLTC/latest/?format=json&market_code=nex"		, TheOnes[].class );
		log.info(Arrays.toString(theones));
		log.info("time is now {}",dateFormat.format(new Date()));
	}


}