package chess.view;

import java.util.Scanner;

public final class InputView {

    private static final Scanner SCAN = new Scanner(System.in);

    public static String option() {
        return SCAN.nextLine();
    }
}
