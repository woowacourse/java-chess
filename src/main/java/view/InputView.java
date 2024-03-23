package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public static final String MOVE_POSITION_REGEX_FORMAT = "^[a-h][1-8]$";

    public List<String> enterStartOrEndOrMoveCommand() {
        String input = scanner.nextLine();
        return List.of(input.split(" "));
    }
}
