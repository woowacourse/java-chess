package chess.view;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommend() {
        return SCANNER.nextLine();
    }
}
