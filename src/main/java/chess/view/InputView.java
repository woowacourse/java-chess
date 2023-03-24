package chess.view;

import static chess.view.OutputView.printCommandLine;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> readCommand() {
        printCommandLine();
        String input = scanner.nextLine();

        return Arrays.stream(input.split(" "))
                .map(String::trim)
                .collect(toList());
    }
}
