package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public static final int KILL_PASSING_DISTANCE = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard chessBoard) {
        //TODO: 조건식 가독성 있게 추상화 및 매직넘버 상수화
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 1) {
            return true;
        }
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 4 && start.isOrthogonalWith(
                destination) && start.isRankSameWith(teamInitialPawnRow())) {
            return true;
        }
        return false;
    }

    boolean isNotReachable(Position start, Position destination) {
        //1칸 전진은 암때나 가능
        //2칸 전진은 초기 위치일때만 가능
        //대각 한칸 이동은 적이 있을 때만 가능
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 1) {
            return true;
        }
        if (isForward(start, destination) && start.squaredDistanceWith(destination) == 4 && start.isOrthogonalWith(
                destination) && start.isRankSameWith(teamInitialPawnRow())) {
            return true;
        }
        return false;
    }

    boolean isPathNotClear(Position start, Position destination, ChessBoard chessBoard) {
        return false;
    }

    boolean isFriendlyPieceAtDestination(Position destination, ChessBoard chessBoard) {
        if (chessBoard.positionIsEmpty(destination)) {
            return false;
        }
        if (chessBoard.findPieceByPosition(destination).isSameTeam(this)) {
            return false;
        }
        return true;
    }

    //TODO: 테스트 구현하
    public boolean isKillPassing(Position start, Position destination) {
        return isForward(start, destination) && start.isDiagonalWith(destination)
                && start.squaredDistanceWith(destination) == KILL_PASSING_DISTANCE;
    }

    private boolean isForward(Position start, Position destination) {
        return start.directionTo(destination) == teamForwardDirection();
    }
}
