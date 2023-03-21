package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    public static List<String> requestCommand() {
        return Arrays.asList(scanner.nextLine().split(DELIMITER));
    }

}
