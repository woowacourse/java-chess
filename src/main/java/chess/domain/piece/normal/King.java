package chess.domain.piece.normal;

import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;
import chess.domain.position.Position;

import java.util.Set;

public final class King extends Normal {

    public King(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (source.isNear(target)) {

            return Set.of(target);
        }

        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.KING;
    }
}
