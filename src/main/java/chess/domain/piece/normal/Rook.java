package chess.domain.piece.normal;

import chess.domain.board.Position;
import chess.domain.piece.property.Color;
import chess.domain.piece.property.Kind;

import java.util.Set;

public final class Rook extends Normal {

    public Rook(final Color color) {
        super(color);
    }

    @Override
    public Set<Position> computePath(final Position source, final Position target) {
        if (source.isFileEquals(target)) {
            return source.generateFilePath(target);
        }
        if (source.isRankEquals(target)) {
            return source.generateRankPath(target);
        }
        throw new IllegalArgumentException("갈 수 없는 위치입니다.");
    }

    @Override
    public Kind getKind() {
        return Kind.ROOK;
    }
}
