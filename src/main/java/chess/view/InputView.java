package chess.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Command readCommand() {
        String input = scanner.nextLine();
        return Command.of(input);
    }

    public static List<String> readPositions() {
        String input = scanner.nextLine();

        return Arrays.stream(input.split(" "))
                .map(String::trim)
                .collect(toList());
    }
}
