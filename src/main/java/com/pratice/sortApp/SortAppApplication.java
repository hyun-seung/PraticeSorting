package com.pratice.sortApp;

import com.pratice.sortApp.controller.MainController;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SortAppApplication {

	public static void main(String[] args) {
//		SpringApplication.run(SortAppApplication.class, args);

		MainController mainController = new MainController();
		mainController.run();
	}

}
