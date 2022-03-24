package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Map;

public abstract class Started implements GameState {

    protected final Board board;
    protected final Color turnColor;

    public Started(Board board, Color turnColor) {
        this.board = board;
        this.turnColor = turnColor;
    }

    @Override
    public final Map<Point, Piece> getPointPieces() {
        return board.getPointPieces();
    }
}
