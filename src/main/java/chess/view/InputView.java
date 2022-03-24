package chess.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public void terminate() {
        scanner.close();
    }

    public String getStartOrEndInput() {
        try {
            String input = scanner.nextLine();
            validateStartOrEndInput(input);
            return input;
        } catch (IllegalArgumentException e) {
            return getStartOrEndInput();
        }
    }

    private void validateStartOrEndInput(String input) {
        if (!input.equals("start") && !input.equals("end")) {
            throw new IllegalArgumentException("start 또는 end를 입력해주세요.");
        }
    }

    public String getCommand() {
        String input = scanner.nextLine();

        if (input.startsWith("move")) {
            String[] commands = input.split(" ");
            validateMoveCommandFormat(commands);
            validateMoveCommandKeyword(commands);
            return input;
        }
        if (input.startsWith("status")) {
            validateStatusInput(input);
            return input;
        }
        throw new IllegalArgumentException("status 혹은 move source위치 target위치 형식으로 입력해주세요.");
    }

    private void validateStatusInput(String input) {
        if (!input.equals("status")) {
            throw new IllegalArgumentException("status 를 정확히 입력해주세요");
        }
    }

    private void validateMoveCommandFormat(String[] commands) {
        if (commands.length != 3) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }

    private void validateMoveCommandKeyword(String[] commands) {
        if (!commands[0].equals("move")) {
            throw new IllegalArgumentException("move source위치 target위치 형식으로 입력해주세요.");
        }
    }
}
