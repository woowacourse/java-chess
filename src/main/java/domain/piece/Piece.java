package domain.piece;

import java.util.List;

import domain.Square;

public abstract class Piece {
    private final TeamColor teamColor;

    public Piece(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public abstract List<Square> findRoutes(Square src, Square dest);

    public abstract double score();

    public abstract PieceType pieceType();

    public boolean isBlack() {
        return teamColor == TeamColor.BLACK;
    }

    public boolean isWhite() {
        return teamColor == TeamColor.WHITE;
    }

    public boolean isDifferentTeam(Piece piece) {
        return teamColor != piece.teamColor;
    }

}
