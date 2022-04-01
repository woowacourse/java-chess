package chess.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public String inputCommand() {
        return scanner.nextLine();
    }
}
