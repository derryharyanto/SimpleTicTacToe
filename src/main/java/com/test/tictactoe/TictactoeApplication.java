package com.test.tictactoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TictactoeApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Integer input;
		do {
			System.out.print("input boardSize= ");
			input = scanner.nextInt();
		}while (input%2==0 || input<3);

		String[][] dimension = new String[input][input];
		GameProcess.process(dimension, input, scanner);
	}

}
