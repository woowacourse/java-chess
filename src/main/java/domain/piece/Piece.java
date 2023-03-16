package domain.piece;

import domain.Square;
import java.util.List;

public abstract class Piece {

    private final TeamColor teamColor;

    protected Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public boolean isBlack() {
        return teamColor == TeamColor.BLACK;
    }

    public abstract List<Square> findRoutes(Square src, Square dest);
}
