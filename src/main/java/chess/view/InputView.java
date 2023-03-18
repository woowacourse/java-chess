package chess.view;

import chess.dto.ChessInputDto;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = " ";

    private InputView() {
    }

    public static ChessInputDto inputGameState() {
        final String command = scanner.nextLine();

        return ChessInputDto.from(command.split(DELIMITER));
    }
}
