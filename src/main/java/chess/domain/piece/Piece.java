package chess.domain.piece;

import chess.domain.Path;
import chess.domain.position.Position;
import chess.domain.TeamColor;
import chess.view.PieceName;
import java.util.List;

public abstract class Piece {

    protected TeamColor color;
    protected PieceName name;

    public Piece(final TeamColor color, final PieceName name) {
        this.color = color;
        this.name = name;
    }

    abstract List<Path> findMovablePaths(Position position);

    public String getName() {
        return name.getName(color);
    }

}
