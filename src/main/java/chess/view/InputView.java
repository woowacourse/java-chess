package chess.view;

import java.util.Scanner;

public final class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommend() {
        return SCANNER.nextLine();
    }
}
