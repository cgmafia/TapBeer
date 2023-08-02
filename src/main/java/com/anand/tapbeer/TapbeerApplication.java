package com.anand.tapbeer;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@SpringBootApplication
@RestController
public class TapbeerApplication {

	Double startTime = 0.0;
	Double stopTime = 0.0;
	Double costPerMl = 0.05;
	Double tapOpenTime;
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
		startTime = 0.0;
		startTime = System.currentTimeMillis() / 1000.0;
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Open Tapbeer");
		map.put("open time",  String.valueOf(Math.round(startTime)));

		return map;
	}

	@GetMapping("/close")
	public HashMap closeTap(){
		if(startTime == 0.0){
			HashMap<String, String> map = new HashMap<>();
			map.put("message", "Beer tap is still not opened");
			return map;
		}
		stopTime = System.currentTimeMillis() / 1000.0;
		tapOpenTime = stopTime - startTime;
		costPerServe = costPerMl * tapOpenTime;

		totalSales += costPerServe;
		totalVolume += tapOpenTime;


		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Closed Tapbeer");
		map.put("open time",  String.valueOf(Math.round(startTime)));
		map.put("close time",  String.valueOf(Math.round(stopTime)));
		map.put("flow time(s)",  String.valueOf(tapOpenTime));
		map.put("Cost beer/ms",  String.valueOf(costPerMl));
		map.put("Total Volume Sold",  totalVolume.toString());
		map.put("cost this serve",  String.valueOf(costPerServe));
		map.put("Total Sales",  totalSales.toString());
		return map;
	}

	@GetMapping("/account")
	public HashMap getAccount(){
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Account status");
		map.put("Cost beer/ms",  String.valueOf(costPerMl));
		map.put("Total Sales",  totalSales.toString());
		map.put("Total Volume Sold",  totalVolume.toString());
		return map;
	}
}
