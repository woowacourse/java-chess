package chess.domain.piece;

import chess.domain.Path;
import chess.domain.TeamColor;
import chess.domain.position.Position;
import chess.view.PieceName;
import java.util.List;
import java.util.Objects;

public abstract class Piece {

    protected TeamColor color;
    protected PieceName name;

    public Piece(final TeamColor color, final PieceName name) {
        this.color = color;
        this.name = name;
    }

    public abstract List<Path> findMovablePaths(Position position);

    public abstract boolean isPawn();

    public String getName() {
        return name.getName(color);
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
        return color == piece.color && name == piece.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, name);
    }
}
