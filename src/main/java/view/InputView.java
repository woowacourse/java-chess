package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String command() {
        return SCANNER.nextLine();
    }

    public static List<String> moveCommand() {
        List<String> command = Arrays.asList(SCANNER.nextLine().split(" "));
        return command;
    }

}
