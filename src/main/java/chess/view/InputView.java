package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> readCommand() {
        String input = scanner.nextLine();
        if (input.equals("start") || input.equals("end")) {
            return List.of(input);
        }
        if (isMoveCommand(input)) {
            return convertMoveCommand(input);
        }
        throw new IllegalArgumentException("잘못된 입력입니다.");
    }

    private boolean isMoveCommand(String input) {
        return input.matches("^move [a-h][1-8] [a-h][1-8]$");
    }

    private List<String> convertMoveCommand(String input) {
        return Arrays.asList(input.split(" "));
    }
}
