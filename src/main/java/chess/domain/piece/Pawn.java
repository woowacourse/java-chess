package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final int DEFAULT_MOVE_DISTANCE = 1;
    private static final int INITIAL_MOVE_DISTANCE = 2;

    public Pawn(Team team) {
        super(team);
    }


    @Override
    boolean canNotMoveByItsOwnInPassing(Position start, Position destination) {
        int distance = start.calculateDistance(destination);
        //폰의 이동 방향이 팀의 전진 방향과 다르다면 이동할 수 없다
        if (!team.isTeamForwardDirectionsContains(Direction.of(start, destination))) {
            return true;
        }
        //폰은 팀의 수직 전진 방향으로 한 칸 이동할 수 있다
        if (distance == DEFAULT_MOVE_DISTANCE && start.isOrthogonalWith(destination)) {
            return false;
        }
        //폰은 팀의 수직 전진 방향으로 두 칸 이동할 수 있다
        if (distance == INITIAL_MOVE_DISTANCE && start.isOrthogonalWith(destination)) {
            return false;
        }
        // 폰은 대각선으로 한 칸 이동할 수 있다
        if (distance == DEFAULT_MOVE_DISTANCE && start.isDiagonalWith(destination)) {
            return false;
        }
        return true;
    }

    @Override
    boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard) {
        int distance = start.calculateDistance(destination);
        // 폰의 이동 경로에 기물이 있다면 이동할 수 없다
        if (chessBoard.isPathHasObstacle(start.calculateSlidingPath(destination))) {
            return true;
        }
        // 폰이 수직으로 이동했고 도착지에 기물이 있다면 이동할 수 없다
        if (!chessBoard.positionIsEmpty(destination) && start.isOrthogonalWith(destination)) {
            return true;
        }
        // 폰이 두칸 수직으로 이동할 때 초기 위치에서 움직이지 않았다면 이동할 수 없다
        if (distance == INITIAL_MOVE_DISTANCE) {
            return !team.isPositionOnTeamInitialPawnRank(start);
        }
        // 다른 팀의 기물이 목적지에 존재하지 않을 경우 폰은 대각선으로 이동할 수 없다.
        if (distance == DEFAULT_MOVE_DISTANCE && start.isDiagonalWith(destination)) {
            return chessBoard.isNoHostilePieceAt(destination, team);
        }
        return false;
    }
}
