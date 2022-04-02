package chess.view.input;

import java.util.Scanner;

public abstract class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }


    public static String inputCommand() {
        return scanner.nextLine();
    }
}
