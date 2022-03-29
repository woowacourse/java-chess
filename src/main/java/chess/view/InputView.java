package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String BLANK = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestCommands() {
        return new ArrayList<>(List.of(scanner.nextLine().split(BLANK)));
    }
}
