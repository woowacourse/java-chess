package chess.view;

import java.util.Scanner;

public class InputView {

    private static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String requestCommand() {
        String command = input().toLowerCase();
        validateNull(command);
        validateCommand(command);

        return command;
    }

    private static void validateNull(String text) {
        if (text == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다.");
        }
    }

    private static void validateCommand(String text) {
        if (!text.equals("start") && !text.equals("end")) {
            throw new IllegalArgumentException("잘못된 명령입니다.");
        }
    }
}
