package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.state.Ready;
import chess.domain.state.State;
import java.util.Map;

public final class Board {

    private State state;

    public Board() {
        state = new Ready();
    }

    public void movePiece(final String sourceValue, final String targetValue) {
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
        state = state.movePiece(sourcePosition, targetPosition);
    }

    public void init() {
        state = new Ready().init();
    }

    public double score(final Color color) {
        return state.score(color);
    }

    public Color winner() {
        return state.winner();
    }

    public Map<Position, Piece> pieces() {
        return state.pieces();
    }

    public boolean isFinish() {
        return state.isFinish();
    }
}
