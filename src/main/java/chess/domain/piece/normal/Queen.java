package chess.domain.piece.normal;

import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.Path;
import chess.domain.position.Position;

import java.util.Set;

public final class Queen extends Normal {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        Path path = Path.of(source, target);
        if (path.isDiagonal() || path.isStraight()) {
            return path.computePath(source, target);
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.QUEEN;
    }
}
