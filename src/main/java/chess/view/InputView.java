package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> inputCommand() {
        String input = scanner.nextLine();
        List<String> command = Arrays.stream(input.split(" ")).collect(Collectors.toList());
        while (command.size() < 3) {
            command.add("");
        }
        return command;
    }
}
