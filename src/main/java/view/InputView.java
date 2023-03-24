package view;

import java.util.List;
import java.util.Scanner;

public final class InputView {

    public static final String DELIMITER = " ";
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> readUserInput() {
        String input = scanner.nextLine();
        return List.of(input.split(DELIMITER));
    }
}
