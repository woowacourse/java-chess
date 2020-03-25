package chess.domain.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }
}
