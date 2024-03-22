package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public static final int KILL_PASSING_DISTANCE = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard board) {
        //TODO: 조건식 가독성 있게 추상화 및 매직넘버 상수화
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 1 && board.positionIsEmpty(destination)) {
            return true;
        }
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 4 && start.isStraightWith(
                destination) && start.rowIs(teamInitialPawnRow()) && board.positionIsEmpty(destination)) {
            return true;
        }

        return isKillPassing(start, destination) && !board.positionIsEmpty(destination) && this.isOtherTeam(board.findPieceByPosition(destination));
    }

    //TODO: 테스트 구현하기
    public boolean isKillPassing(Position start, Position destination) {
        return isForward(start, destination) && start.isDiagonalWith(destination)
                && start.squaredDistanceWith(destination) == KILL_PASSING_DISTANCE;
    }

    private boolean isForward(Position start, Position destination) {
        return start.directionTo(destination) == teamForwardDirection();
    }
}
