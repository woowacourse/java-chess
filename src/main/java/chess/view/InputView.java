package chess.view;

import chess.dto.CommandDto;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static CommandDto readStateCommand() {
        String input = scanner.nextLine();
        Command command = Command.findCommandByName(input);
        return new CommandDto(command, input);
    }
}
