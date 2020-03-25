package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static boolean inputStartCommand() {
		System.out.println(
			"체스 게임을 시작합니다. \n " +
				"게임 시작은 start, 종료는 end 명령을 입력하세요.");
		String input = SCANNER.nextLine();
		if (input.equals("start")) {
			return true;
		}
		if (input.equals("end")) {
			return false;
		}
		throw new InputMismatchException("잘못된 시작 커맨드를 입력했습니다.");
	}
}
