package chess.view;

import java.util.List;
import java.util.Scanner;


public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMETER = " ";
    private static final int LIMIT = -1;

    private InputView() {
    }

    public static List<String> askStart() {

        return List.of(scanner.nextLine().split(DELIMETER, LIMIT));
    }

    public static List<String> read() {
        return List.of(scanner.nextLine().split(DELIMETER, LIMIT));
    }
}
