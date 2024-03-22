package domain.piece;

import domain.position.Position;

public class Rook extends AbstractPiece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public void validateMovement(Position resource, Position target, Piece other) {
        validateColorDifference(other);

        int rankGap = resource.calculateRankGap(target);
        int fileGap = resource.calculateFileGap(target);
        if (rankGap == 0 && fileGap == 0) {
            throw new IllegalArgumentException(String.format("%s은 수평, 수직 방향으로만 이동할 수 있습니다.",
                    this.getClass().getSimpleName()));
        }
        if (rankGap * fileGap == 0) {
            return;
        }
        throw new IllegalArgumentException(String.format("%s은 수평, 수직 방향으로만 이동할 수 있습니다.",
                this.getClass().getSimpleName()));
    }

    private void validateColorDifference(final Piece other) {
        if (this.getColor().equals(other.getColor())) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }
    }

    @Override
    public Type getType() {
        return Type.ROOK;
    }
}
