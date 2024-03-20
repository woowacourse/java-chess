package chess.domain.piece;

import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination) {
        //폰의 전진방향이 팀의 전진방향과 같고 움직이는 양이 1이면 움직일 수 있다
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 1) {
            return true;
        }

        //폰의 전진양이 2이고 폰이 초기위치 이면 전진할 수 있다.
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 2 && start.isStraightWith(
                destination) && start.rowIs(teamInitialPawnRow())) {
            return true;
        }
        //잡아먹는 경우 (위 대각선 두방향도 일단 true 반환)
        return false;
    }

    private boolean isForward(Position start, Position destination) {
        return start.directionTo(destination) == teamForwardDirection();
    }
}
