package chess;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.piece.Color;
import chess.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessController {
    private static final String START_COMMAND = "start";
    private final Board board;

    public ChessController() {
        this.board = new BoardFactory().createInitialBoard();
    }

    public void run() {
        String command = InputView.readStartCommand();
        if (!command.equals(START_COMMAND)) {
            return;
        }

        Color color = Color.WHITE;
        while (true) {
            printBoard();
            movePiece(color);
            color = changeTurn(color);
        }
    }

    private void printBoard() {
        OutputView.printBoard(board.board());
    }

    private void movePiece(final Color turn) {
        List<String> moveCommand = InputView.readMoveCommand();

        Position from = searchPosition(moveCommand.get(0));
        Position to = searchPosition(moveCommand.get(1));

        board.move(from, to, turn);
    }

    private Position searchPosition(final String command) {
        int fromFile = command.charAt(0) - 'a' + 1;
        int fromRank = command.charAt(1) - '0';

        return new Position(fromFile, fromRank);
    }

    private Color changeTurn(Color color) {
        return color.opposite();
    }
}
