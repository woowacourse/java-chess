package chess.domain.state;

import chess.domain.board.Pieces;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.List;
import java.util.Map;

public abstract class AbstractState implements State {

    protected final Pieces pieces;
    protected Color color;

    protected AbstractState(final Color color, final Pieces pieces) {
        this.color = color;
        this.pieces = pieces;
    }

    @Override
    public boolean isSameColor(final Piece piece) {
        return piece.isSameColor(color);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public State init() {
        throw new IllegalArgumentException("ready 상태에서만 초기화가 가능합니다.");
    }

    @Override
    public double score(final Color color) {
        return pieces.score(color);
    }

    @Override
    public Map<Position, Piece> pieces() {
        return pieces.pieces();
    }

    @Override
    public State movePiece(final Position sourcePosition, final Position targetPosition) {
        throw new IllegalArgumentException("게임진행중이 아닙니다.");
    }

    @Override
    public Color winner() {
        return color;
    }

    @Override
    public Color turn() {
        return color;
    }

    @Override
    public List<Position> movablePositions(String source) {
        Position sourcePosition = Position.of(source);
        return pieces.movablePositions(sourcePosition);
    }
}
