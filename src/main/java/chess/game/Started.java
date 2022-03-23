package chess.game;

import chess.board.Board;
import chess.board.Point;
import chess.piece.Piece;

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
