package chess.view;

import java.util.Scanner;

public class InputView {

    private static String input() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String requestCommand() {
        try {
            return getCommand();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] : " + e.getMessage());
            return requestCommand();
        }
    }

    private static String getCommand() {
        String text = input().toLowerCase();
        validateNotNull(text);

        Command.validateCommand(text);

        return text;
    }

    private static void validateNotNull(String text) {
        if (text == null) {
            throw new IllegalArgumentException("null은 허용되지 않습니다.");
        }
    }
}
