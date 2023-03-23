package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Position;

public final class UnMovablePiece extends Piece {

    public UnMovablePiece(final Color color) {
        super(color);
    }

    public static UnMovablePiece create() {
        return new UnMovablePiece(Color.EMPTY);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Piece targetPiece) {
        throw new IllegalStateException("시작점에 기물이 존재하지 않습니다.");
    }
}
