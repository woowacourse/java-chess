package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static String getFirstCommand() {
        String commandInput = scanner.nextLine();
        validateFirstCommandInput(commandInput);
        return commandInput;
    }

    private static void validateFirstCommandInput(String input) {
        if (!input.equals("start") && !input.equals("end")) {
            throw new IllegalArgumentException("start 혹은 end 를 입력해주세요.");
        }
    }

    public static String getCommand() {
        String input = scanner.nextLine();
        if (input.startsWith("move")) {
            String[] commands = input.split(" ");
            validateMoveCommandFormat(commands);
            validateMoveCommandKeyword(commands);
            return input;
        }
        if (input.equals("status") || input.equals("end")) {
            return input;
        }
        throw new IllegalArgumentException("정확한 명령을 입력해주세요.");
    }

    private static void validateMoveCommandFormat(String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("move source 위치 target 위치 형식으로 입력해주세요.");
        }
    }

    private static void validateMoveCommandKeyword(String[] commands) {
        if (!commands[0].equals("move")) {
            throw new IllegalArgumentException("move source 위치 target 위치 형식으로 입력해주세요.");
        }
    }
}
