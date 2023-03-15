package chess.domain.piece;

import chess.domain.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;

public abstract class Piece {

    protected TeamColor color;

    public Piece(final TeamColor color) {
        this.color = color;
    }

    abstract List<Path> findMovablePaths(Position position);

}
