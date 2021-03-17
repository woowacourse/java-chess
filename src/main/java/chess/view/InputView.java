package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static String[] takeInput() {
        String input = SCANNER.nextLine();
        if ("start".equals(input) || "end".equals(input)) {
            return new String[]{input};
        }
        String[] splitInput = input.split(" ");
        if ("move".equals(splitInput[0])) {
            return splitInput;
        }
        throw new IllegalArgumentException();
    }
}
