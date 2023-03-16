package chess.domain.piece;

import chess.domain.Path;
import chess.domain.TeamColor;
import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected TeamColor color;
    protected PieceType type;

    public Piece(final TeamColor color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public abstract List<Path> findMovablePaths(Position position);

    public abstract boolean isPawn();

    public boolean isSameColor(TeamColor color) {
        return this.color == color;
    }

    public TeamColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
