package techcourse.fp.chess.domain.piece.ordinary;

import java.util.List;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.movingStrategy.MovingStrategy;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.PieceType;

public abstract class OrdinaryPiece extends Piece {

    protected final MovingStrategy movingStrategy;

    public OrdinaryPiece(final Color color, final PieceType pieceType,
                         final MovingStrategy movingStrategy) {
        super(color, pieceType);
        this.movingStrategy = movingStrategy;
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        if (isAlly(targetPiece)) {
            throw new IllegalArgumentException("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        return movingStrategy.createPath(source, target);
    }

    private boolean isAlly(Piece otherPiece) {
        return color.isSameColor(otherPiece.getColor());
    }
}
