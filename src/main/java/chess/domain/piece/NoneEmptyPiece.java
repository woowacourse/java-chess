package chess.domain.piece;

import chess.domain.Team;
import chess.domain.movement.Movement;
import chess.domain.piece.movable.Movable;
import chess.domain.position.RelativePosition;

public abstract class NoneEmptyPiece extends Piece implements Movable {

    protected final Movement movement;

    public NoneEmptyPiece(PieceType pieceType, Team team, Movement movement) {
        super(pieceType, team);
        this.movement = movement;
    }

    @Override
    public abstract boolean isMobile(RelativePosition relativePosition, Piece target);

    protected void validateSameTeam(Piece target){
        if(isSameTeam(target)){
            throw new IllegalArgumentException("이동하고자 하는 자리에 같은 팀이 존재합니다.");
        }
    }

}
