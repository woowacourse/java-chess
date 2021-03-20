package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.Map;

public final class Board {

    private final Pieces pieces = new Pieces();
    private State state;

    public Board() {
        state = new State();
        state.finish();
    }

    public void movePiece(final String sourceValue, final String targetValue) {
        // todo - 상태패턴에게 책임 넘기기
        if (state.isFinish()) {
            throw new IllegalArgumentException("게임진행중이 아닙니다.");
        }
        Position sourcePosition = Position.of(sourceValue);
        Position targetPosition = Position.of(targetValue);
        pieces.movePiece(sourcePosition, targetPosition, state);
    }

    public void init() {
        pieces.init();
        state = new State();
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
