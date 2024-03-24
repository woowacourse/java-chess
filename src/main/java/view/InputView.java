package view;

import service.command.ChessCommand;

import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public ChessCommand readCommand() {
        System.out.println();

        final String input = SCANNER.nextLine();
        return CommandFormat.createCommand(input);
    }
}
