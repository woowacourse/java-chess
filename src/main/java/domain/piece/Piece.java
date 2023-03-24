package domain.piece;

import domain.Square;
import java.util.List;

public abstract class Piece {

    private final TeamColor teamColor;
    private final PieceInfo pieceInfo;

    protected Piece(TeamColor teamColor, PieceInfo pieceInfo) {
        this.teamColor = teamColor;
        this.pieceInfo = pieceInfo;
    }

    public boolean isBlack() {
        return teamColor.isBlack();
    }

    public abstract List<Square> findRoutes(Square src, Square dest);

    public PieceInfo getPieceType() {
        return pieceInfo;
    }

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
