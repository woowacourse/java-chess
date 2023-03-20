package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;

public abstract class NoneEmptyPiece implements Piece {

    protected final Team team;
    protected final Movement movement;

    public NoneEmptyPiece(Team team, Movement movement) {
        this.team = team;
        this.movement = movement;
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition) {
        return movement.isMobile(relativePosition);
    }

    @Override
    public boolean isBlack() {
        return team.isBlack();
    }

    @Override
    public boolean isWhite() {
        return team.isWhite();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    @Override
    public abstract PieceType getType();
}
