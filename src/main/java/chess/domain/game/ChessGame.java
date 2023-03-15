package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;

public class ChessGame {

    private final Board board;

    private ChessGame(final Board board) {
        this.board = board;
    }

    public static ChessGame initialize() {
        return new ChessGame(Board.initialize());
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
