package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Piece;

import java.util.Map;

public abstract class Started implements GameState {

    protected final Board board;

    public Started(Board board) {
        this.board = board;
    }

    @Override
    public final Map<Point, Piece> getPointPieces() {
        return board.getPointPieces();
    }
}
