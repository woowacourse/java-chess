package chess.controller;

import static chess.view.Command.END;
import static chess.view.Command.START;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class MainController {

    private final ChessGame chessGame;

    public MainController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        Command firstCommand = InputView.readCommand();

        if (firstCommand == START) {
            OutputView.printBoard(chessGame.getBoard());

            while (true) {
                List<String> input = InputView.readPositions();
                if (Command.of(input.get(0)) == END) {
                    break;
                }

                Position current = toPosition(input.get(1));
                Position target = toPosition(input.get(2));
                chessGame.movePiece(current, target);

                OutputView.printBoard(chessGame.getBoard());
            }
        }

        OutputView.printFinishMessage();
    }

    private Position toPosition(final String input) {
        char first = input.charAt(0);
        int col = Math.abs(first - 97);

        char second = input.charAt(1);
        int row = Math.abs(Character.getNumericValue(second) - 8);

        return new Position(row, col);
    }
}
