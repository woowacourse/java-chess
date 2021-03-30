package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";
    public static final String STATUS = "status";

    private InputView() {
    }

    public static String input() {
        return SCANNER.nextLine();
    }
}
