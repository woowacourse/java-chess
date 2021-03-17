package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MoveVO;
import chess.domain.Team;

public class Bishop implements Piece{
    private static final int MOVE_RANGE = 8;
    private final Team team;

    public Bishop(Team team) {
        this.team = team;
    }

    @Override
    public Team getTeam() {
        return team;
    }

    @Override
    public MoveVO strategy() {
        return new MoveVO(Direction.diagonalDirection(), MOVE_RANGE);
    }

    @Override
    public void canMove() {

    }

    @Override
    public String getName() {
        if (team == Team.BLACK) {
            return "B";
        }
        return "b";
    }

    @Override
    public void checkTurn(Team team) {
        if (this.team != team) {
            throw new IllegalArgumentException("[ERROR] 상대 팀의 차례입니다.");
        }
    }

    @Override
    public boolean isSameTeam(Team team) {
        return this.team == team;
    }
}
