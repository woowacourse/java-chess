package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String COMMAND_DELIMITER = " ";

    public static List<String> requestCommand() {
        return Arrays.asList(scanner.nextLine().split(COMMAND_DELIMITER));
    }
}
