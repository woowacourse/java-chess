package chess;

import chess.piece.Piece;
import chess.view.OutputView;

import java.util.Map;

public class ChessController {

    public void run() {
        final BoardFactory boardFactory = new BoardFactory();

        final Board board = new Board(boardFactory);
        final Map<Position, Piece> boardMap = board.board();

        OutputView.printBoard(boardMap);
    }
}
