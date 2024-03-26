package chess.domain.piece;

import chess.domain.Position;
import java.util.List;

public class Empty extends Piece {

    private static final Piece piece = Empty.of();

    private Empty(final Color color) {
        super(color);
    }

    public static Empty of() {
        return new Empty(Color.WHITE);
    }

    @Override
    public List<Position> findPath(Position source, Position target) {
        return null;
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
