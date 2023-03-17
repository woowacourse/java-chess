package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public List<String> inputCommand() {
        String command = scanner.nextLine();
        return Arrays.stream(command.split(" ")).collect(Collectors.toList());
    }
}
