package view;

import static domain.EndCommand.*;
import static domain.StartCommand.*;

import java.util.Scanner;

import domain.Command;
import domain.MoveCommand;

public class InputView {
	public static Command readStartOrEnd() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		while (!input.equals("start") && !input.equals("end")) {
			System.out.println("다시 입력해 주세요");
			input = scanner.nextLine();
		}
		if (input.equals("end")) {
			return END_COMMAND;
		}
		return START_COMMAND;
	}

	public static Command readEndOrMove() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		while (!input.startsWith("move") && !input.equals("end")) {
			System.out.println("다시 입력해 주세요");
			input = scanner.nextLine();
		}
		if (input.equals("end")) {
			return END_COMMAND;
		}
		String options = input.substring(4);
		return new MoveCommand(options);
	}
}
