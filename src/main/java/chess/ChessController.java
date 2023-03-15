package chess;

import chess.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Map;

public class ChessController {

    private static final String START_COMMAND = "start";

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();

        final Board board = new Board(boardFactory);
        final Map<Position, Piece> boardMap = board.board();

        String command = InputView.readStartCommand();

        while (command.equals(START_COMMAND)) {
            OutputView.printBoard(boardMap);

            command = InputView.readStartCommand();
        }
    }
}
