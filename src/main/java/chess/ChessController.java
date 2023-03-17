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
        BoardFactory boardFactory = new BoardFactory();
        this.board = new Board(boardFactory);
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

    private static Position searchPosition(final List<String> moveCommand, final int index) {
        String from = moveCommand.get(index);

        int fromFile = from.charAt(0) - 'a' + 1;
        int fromRank = from.charAt(1) - '0';
        return new Position(fromFile, fromRank);
    }

    private void printBoard() {
        OutputView.printBoard(board.board());
    }

    private void movePiece(final Color turn) {
        List<String> moveCommand = InputView.readMoveCommand();
        Position from = searchPosition(moveCommand, 0);
        Position to = searchPosition(moveCommand, 1);
        board.move(from, to, turn);
    }

    private static Color changeTurn(Color color) {
        return color.opposite();
    }
}
