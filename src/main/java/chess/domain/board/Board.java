package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.state.Play;
import chess.domain.state.State;
import java.util.Map;

public final class Board {

    private final Pieces pieces = new Pieces();
    private State state;

    public Board() {
        state = new Play();
    }

    public void movePiece(final String sourceValue, final String targetValue) {
        state.validateMove();
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
        pieces.movePiece(sourcePosition, targetPosition, state);
        if (pieces.isKillKing()) {
            state = state.nextState();
        }
        state.nextTurn();
    }

    public void init() {
        pieces.init();
        state = new Play();
    }

    public double score(final Color color) {
        return pieces.score(color);
    }

    public Color winner() {
        return state.winner();
    }

    public Map<Position, Piece> pieces() {
        return pieces.pieces();
    }

    public boolean isFinish() {
        return state.isFinish();
    }
}
