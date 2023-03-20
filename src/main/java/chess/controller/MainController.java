package chess.controller;

import static chess.view.Command.END;
import static chess.view.Command.START;
import static chess.view.InputView.readCommand;
import static chess.view.InputView.readPositions;
import static chess.view.OutputView.printFinishMessage;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.view.Command;
import chess.view.OutputView;
import java.util.List;

public class MainController {

    private final ChessGame chessGame;

    public MainController(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public void run() {
        OutputView.printGameStart();

        Command firstCommand = readCommand();
        if (firstCommand == START) {
            OutputView.printBoard(chessGame.getBoard());
            playChess();
        }

        printFinishMessage();
    }

    private void playChess() {
        while (true) {
            List<String> input = readPositions();

            if (Command.of(input.get(0)) == END) {
                break;
            }

            Position current = toPosition(input.get(1));
            Position target = toPosition(input.get(2));

            chessGame.movePiece(current, target);
            OutputView.printBoard(chessGame.getBoard());
        }
    }

    private Position toPosition(final String input) {
        char first = input.charAt(0);
        int col = Math.abs(first - 97);

        char second = input.charAt(1);
        int row = Math.abs(Character.getNumericValue(second) - 8);

        return new Position(row, col);
    }
}
