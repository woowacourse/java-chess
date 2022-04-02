package chess.view;

import java.util.Scanner;

public final class Console {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Console() {
    }

    public static String scan() {
        return SCANNER.nextLine();
    }
}
