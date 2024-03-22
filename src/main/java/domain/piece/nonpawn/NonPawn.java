package domain.piece.nonpawn;

import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;

public abstract class NonPawn implements Piece { // todo 같은 색 기물 존재 테스트
    private final Color color;

    protected NonPawn(Color color) {
        this.color = color;
    }

    @Override
    public void validateMovement(Position source, Position target, Piece other) {
        source.validateDifferentPosition(target);
        validateDirection(source, target);
        validateMoveCount(source, target);
        validateDifferentColorFromOtherPiece(other);
    }

    private void validateDifferentColorFromOtherPiece(Piece other) {
        if (this.color() == other.color()) {
            throw new IllegalArgumentException("같은 색의 기물이 존재합니다.");
        }
    }

    protected abstract void validateDirection(Position source, Position target);

    protected abstract void validateMoveCount(Position source, Position target);

    @Override
    public Color color() {
        return color;
    }
}
