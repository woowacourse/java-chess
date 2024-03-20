package view;

import java.util.Arrays;
import java.util.Scanner;

import domain.Command;

public class InputView {
	public Command readCommand() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		String[] split = input.split(" ");
		if (split.length != 1) {
			String[] options = Arrays.copyOfRange(split, 1, split.length);
			return new Command(split[0], options);
		}
		return new Command(input);
	}
}
