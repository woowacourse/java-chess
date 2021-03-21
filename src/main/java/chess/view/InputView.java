package chess.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String[] requestInput() {
        String userInput = scanner.nextLine();
        return userInput.split(" ");
    }

    public static boolean willNotPlayGame() {
        String userInput = scanner.nextLine();
        if ("start".equals(userInput)) {
            return false;
        }
        if ("end".equals(userInput)) {
            return true;
        }
        throw new IllegalArgumentException();
    }

    public static boolean willWatchScore() {
        String userInput = scanner.nextLine();
        if ("status".equals(userInput)) {
            return true;
        }
        if ("end".equals(userInput)) {
            return false;
        }
        throw new IllegalArgumentException();
    }
}
