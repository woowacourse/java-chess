package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

public abstract class Piece {
    Team team;

    Piece(Team team) {
        this.team = team;
    }

    public abstract boolean isMovable(Spot startSpot, Spot endSpot);

    public boolean canAttack(Piece piece) {
        return team != piece.team;
    }
}
