package chess.view;

import java.util.Scanner;

public class InputView {

    public static String promptUserSelection() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
