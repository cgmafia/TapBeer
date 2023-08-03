package com.anand.tapbeer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
public class TapbeerApplication {

	Long startTime;
	Long startTimeinSeconds;
	Long stopTime;
	Long stopTimeinSeconds;
	Double costPerMl = 0.5;
	Long tapOpenTime;
	Double costPerServe;
	Double totalSales = 0.0;
	Double totalVolume = 0.0;


	public static void main(String[] args) {
		SpringApplication.run(TapbeerApplication.class, args);
	}

	@GetMapping("/")
	public HashMap welcome(){
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Welcome to Tapbeer");
		return map;
	}

	@GetMapping("/open")
	public HashMap openTap(){
		startTime = 000000000L;
		startTime = System.currentTimeMillis();
		startTimeinSeconds = TimeUnit.MILLISECONDS.toSeconds(startTime);
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Open Tapbeer");

		return map;
	}

	@GetMapping("/close")
	public HashMap closeTap(){
		if(startTime == null){
			HashMap<String, String> map = new HashMap<>();
			map.put("message", "Beer tap is still not opened");
			return map;
		}
		stopTime = System.currentTimeMillis();
		stopTimeinSeconds = TimeUnit.MILLISECONDS.toSeconds(stopTime);
		tapOpenTime = stopTimeinSeconds - startTimeinSeconds;
		costPerServe = costPerMl * tapOpenTime;

		totalSales += costPerServe;
		totalVolume += tapOpenTime;


		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Closed Tapbeer");
		map.put("flow time(s)",  String.valueOf(tapOpenTime));
		map.put("Cost beer/ms",  String.valueOf(costPerMl));
		map.put("Your bill",  String.valueOf(costPerServe));
		map.put("Total Volume Sold Today",  totalVolume.toString());
		map.put("Total Sales Today",  totalSales.toString());
		return map;
	}

	@GetMapping("/account")
	public HashMap getAccount(){
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Account status");
		map.put("Cost beer/ms",  String.valueOf(costPerMl));
		map.put("Total Sales",  (String.valueOf(totalSales)));
		map.put("Total Volume Sold",  String.valueOf(totalVolume));
		return map;
	}
}
