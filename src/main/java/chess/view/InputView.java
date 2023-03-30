package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputCommand() {
        String command = scanner.nextLine();
        return Arrays.stream(command.split(" ")).collect(Collectors.toList());
    }
}
