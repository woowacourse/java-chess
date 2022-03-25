package chess.view;

import chess.Command;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Command inputPlayerCommand() {
        return Command.of(SCANNER.nextLine());
    }
}
