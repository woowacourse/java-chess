package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import chess.domain.position.RowPosition;

public abstract class Piece {
    private final Team team;

    public Piece(Team team) {
        this.team = team;
    }

    public abstract boolean canMove(Position start, Position destination);

    public boolean isBlackTeam() {
        return team == Team.BLACK;
    }

    public Direction teamForwardDirection() {
        return team.getDirection();
    }

    //TODO: 기물 하나가 팀의 전략을 아는 느낌인데 개선 가능한지 확인해보
    public RowPosition teamInitialPawnRow() {
        return team.getInitialPawnRow();
    }
}
