package domain.piece;

import domain.Square;
import java.util.List;

public abstract class Piece {

    private final TeamColor teamColor;

    protected Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public boolean isBlack() {
        return teamColor.isBlack();
    }

    public abstract List<Square> findRoutes(Square src, Square dest);

    public abstract PieceType getPieceType();

    public boolean isDifferentTeam(Piece piece) {
        return teamColor.isDifferent(piece.teamColor);
    }

    public boolean isSameColor(TeamColor color) {
        return teamColor.isSame(color);
    }

    public boolean isNotBlank() {
        return getPieceType().isNotBlank();
    }

    public boolean isPawn() {
        return getPieceType().isPawn();
    }
}
