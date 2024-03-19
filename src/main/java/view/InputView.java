package view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        String command = scanner.nextLine();
        validateCommand(command);
        return command;
    }

    private static void validateCommand(final String command) {
        if (command == null || command.isBlank() || command.isEmpty()) {
            throw new IllegalArgumentException("잘못된 명령어 입력입니다.");
        }
    }
}
