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

    public List<Square> findRoutes(Square source, Square destination, Piece pieceOfDestination) {
        return findRoutes(source, destination);
    }

    protected abstract List<Square> findRoutes(Square source, Square destination);

    public PieceInfo getPieceType() {
        return pieceInfo;
    }

    public boolean isDifferentTeam(Piece piece) {
        return teamColor.isDifferent(piece.teamColor);
    }

    public boolean isSameTeam(Piece piece) {
        return teamColor.isSame(piece.teamColor);
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

    public boolean isBlank() {
        return false;
    }

    public boolean isKing() {
        return false;
    }
}
