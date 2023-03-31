package chess.domain.piece.normal;

import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.Path;
import chess.domain.position.Position;

import java.util.Set;

public final class Bishop extends Normal {

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        Path path = Path.of(source, target);
        if (path.isDiagonal()) {
            return path.computePath(source, target);
        }

        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.BISHOP;
    }
}
