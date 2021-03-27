package chess.view;

import chess.domain.command.Commands;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static Commands inputCommand() {
        return new Commands(SCANNER.nextLine());
    }
}
