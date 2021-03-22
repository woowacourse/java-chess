package chess.view;

import chess.domain.board.Commands;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static String inputCommand() {
        return SCANNER.nextLine();
    }

    public static Commands inputCommand2() {
        return new Commands(SCANNER.nextLine());
    }
}
