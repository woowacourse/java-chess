package view;

import java.util.Scanner;

public class InputView {
	public String readStartOrEnd() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		while (!input.equals("start") && !input.equals("end")) {
			System.out.println("다시 입력해 주세요");
			input = scanner.nextLine();
		}
		return input;
	}

	public String readEndOrMove() {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		while (!input.startsWith("move") && !input.equals("end")) {
			System.out.println("다시 입력해 주세요");
			input = scanner.nextLine();
		}
		return input;
	}
}
