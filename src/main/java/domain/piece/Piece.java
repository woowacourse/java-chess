package domain.piece;

import java.util.List;

import domain.Square;

public abstract class Piece {

    private final TeamColor teamColor;

    protected Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public boolean isBlack() {
        return teamColor == TeamColor.BLACK;
    }

    public abstract List<Square> findRoutes(Square src, Square dest);

    public boolean isDifferentTeam(Piece piece) {
        return teamColor != piece.teamColor;
    }
}
