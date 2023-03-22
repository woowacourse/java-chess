package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.movable.Movable;
import chess.domain.position.RelativePosition;

public abstract class NoneEmptyPiece extends Piece implements Movable {

    protected final Team team;
    protected final Movement movement;

    public NoneEmptyPiece(PieceType pieceType, Team team, Movement movement) {
        super(pieceType);
        this.team = team;
        this.movement = movement;
    }

    @Override
    public abstract boolean isMobile(RelativePosition relativePosition);

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

}
