package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String START_COMMAND = "start";
    public static final String END_COMMAND = "end";
    public static final String MOVE_COMMAND = "move";

    public List<String> readCommand() {
        return Arrays.asList(SCANNER.nextLine().split(" "));
    }
}
