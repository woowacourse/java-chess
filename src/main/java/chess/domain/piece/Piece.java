package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;
import chess.domain.Team;

public abstract class Piece {

    protected final Team team;
    protected final Movement movement;

    public Piece(Team team, Movement movement) {
        this.team = team;
        this.movement = movement;
    }

    public boolean isMobile(RelativePosition relativePosition) {
        return movement.isMobile(relativePosition);
    }

    public boolean isBlack() {
        return team.isBlack();
    }

    public boolean isWhite() {
        return team.isWhite();
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public abstract PieceType getType();
}
