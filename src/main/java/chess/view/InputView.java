package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final int START_AND_END_COMMAND_SIZE = 1;
    private static final int MOVE_COMMAND_SIZE = 3;
    
    private final Scanner scanner = new Scanner(System.in);


    public List<String> readCommand() {
        String command = scanner.nextLine().trim();
        List<String> splitCommand = Arrays.stream(command.split(" ")).toList();
        validateCommandSize(splitCommand);
        return Arrays.stream(command.split(" ")).toList();
    }

    private void validateCommandSize(final List<String> splitInput) {
        if (splitInput.size() != START_AND_END_COMMAND_SIZE && splitInput.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }
}
