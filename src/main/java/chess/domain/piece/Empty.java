package chess.domain.piece;

import chess.domain.Position;
import java.util.List;

public class Empty extends Piece {

    private static final Empty empty = new Empty();

    private Empty() {
        super(null);
    }

    public static Empty of() {
        return empty;
    }

    @Override
    public List<Position> findPath(final Position source, final Position target) {
        throw new IllegalArgumentException("[ERROR] 빈 기물은 이동할 수 없습니다.");
    }

    @Override
    public String getOwnPieceTypeName() {
        return PieceType.EMPTY.name();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
