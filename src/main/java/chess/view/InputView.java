package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputOption() {
        try {
           return InputOption.from(SCANNER.nextLine());
        } catch (IllegalArgumentException exception) {
            OutputView.printError(exception.getMessage());
            return inputOption();
        }
    }
}
