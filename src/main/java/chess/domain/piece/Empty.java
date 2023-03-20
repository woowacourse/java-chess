package chess.domain.piece;

import chess.domain.movement.Movement;
import chess.domain.position.RelativePosition;
import chess.domain.Team;

public class Empty extends Piece {

    public Empty() {
        super(Team.EMPTY, Movement.EMPTY);
    }

    @Override
    public boolean isMobile(RelativePosition relativePosition) {
        throw new IllegalStateException("이동이 불가능한 기물입니다.");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public PieceType getType() {
        return PieceType.EMPTY;
    }
}
