package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.List;

public abstract class Piece {
    final TeamColor teamColor;
    final PieceType pieceType;
    int moveCount;

    Piece(TeamColor teamColor, PieceType pieceType) {
        this.teamColor = teamColor;
        this.pieceType = pieceType;
    }

    public abstract boolean availablePath(Position start, Position target);

    public abstract List<Position> findAllRouteToTarget(Position start, Position target);

    public abstract boolean canMove(List<Piece> piecesOnRoute, Position start, Position target);

    public abstract boolean isEmpty();

    public boolean isOtherTeam(Piece other) {
        return this.teamColor != other.teamColor;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public void incrementMoveCount() {
        moveCount++;
    }
}
