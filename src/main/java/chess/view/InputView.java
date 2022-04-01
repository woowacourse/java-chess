package chess.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String BLANK = " ";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestCommands() {
        return split(scanner.nextLine(), BLANK);
    }

    public static List<String> split(final String input, final String regex) {
        return new ArrayList<>(List.of(input.split(regex)));
    }
}
