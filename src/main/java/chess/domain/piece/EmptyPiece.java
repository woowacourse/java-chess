package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Objects;

public class EmptyPiece implements Piece {

    private final String name = ".";

    @Override
    public boolean isMovable(final Position from, final Position to, final boolean isEmptyTarget) {
        return false;
    }

    @Override
    public boolean isSameColor(final Color color) {
        return false;
    }

    @Override
    public boolean isJumpable() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmptyPiece that = (EmptyPiece) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String getName() {
        return name;
    }

}
