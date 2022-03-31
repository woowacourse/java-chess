package chess.console.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String requestToInputCommand() {
        return scanner.nextLine();
    }

}
