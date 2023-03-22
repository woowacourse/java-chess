package chess.view;

import java.util.Scanner;

import chess.view.dto.SquareDto;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static Command readCommand() {
        try {
            String command = scanner.next();
            return Command.from(command);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return readCommand();
        }
    }

    public static SquareDto readSquare() {
        String square = scanner.next();
        return SquareDto.of(square);
    }
}
