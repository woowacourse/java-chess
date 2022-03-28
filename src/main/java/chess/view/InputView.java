package chess.view;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> requestCommand() {
        String input = SCANNER.nextLine();
        return List.of(input.split(" "));
    }
}
