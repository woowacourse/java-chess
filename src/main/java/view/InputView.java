package view;

import java.util.Scanner;

public class InputView {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    private final Scanner scanner = new Scanner(System.in);

    public boolean readStartCommand() {
        System.out.printf("게임 시작은 %s, 종료는 %s 명령을 입력하세요.%n", START_COMMAND, END_COMMAND);
        String command = scanner.nextLine();
        if (command.equals(START_COMMAND)) {
            return true;
        }
        if (command.equals(END_COMMAND)) {
            return false;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }
}
