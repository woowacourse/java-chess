package techcourse.fp.chess.domain.piece;

import java.util.List;
import techcourse.fp.chess.domain.Color;
import techcourse.fp.chess.domain.Position;

public final class UnMovablePiece extends Piece {

    public UnMovablePiece() {
        super(Color.EMPTY);
    }

    @Override
    public List<Position> findPath(final Position source, final Position target, final Color targetColor) {
        throw new IllegalStateException("시작점에 기물이 존재하지 않습니다.");
    }
}
