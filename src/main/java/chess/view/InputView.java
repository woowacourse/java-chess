package chess.view;

import chess.dto.CommandDto;

import java.util.Scanner;

public final class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    private InputView() {
    }

    public static CommandDto inputGameState() {
        final String command = scanner.nextLine();

        return CommandDto.from(command.split(DELIMITER));
    }
}
