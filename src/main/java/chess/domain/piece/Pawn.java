package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Pawn extends Piece {
    private static final int KILL_PASSING_DISTANCE = 1;
    private static final int DEFAULT_MOVE_DISTANCE = 1;
    private static final int INITIAL_MOVE_DISTANCE = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    boolean canNotMoveByItsOwnInPassing(Position start, Position destination) {
        if (team.isTeamForwardDirectionsContains(Direction.of(start, destination))) {
            return false;
        }
        return true;
    }

    @Override
    boolean canNotMoveByBoardStatus(Position start, Position destination, ChessBoard chessBoard) {
        //팀의 전진방향과 맞도록 한칸 움직이는 것은 가능하다
        if (start.calculateDistance(destination) == DEFAULT_MOVE_DISTANCE && start.isOrthogonalWith(destination)) {
            return false;
        }
        //폰이 초기위치에 있고 팀의 전진방향과 맞도록 두칸 움직이는 것은 가능 하다
        if (start.calculateDistance(destination) == INITIAL_MOVE_DISTANCE && start.isOrthogonalWith(destination)
                && team.isPositionOnTeamInitialPawnRank(start)) {
            return false;
        }
        //팀의 전진방향과 맞고 대각선으로 1칸 움직일 때 목적지에 적이 있으면 움직일 수 있다
        if (start.calculateDistance(destination) == KILL_PASSING_DISTANCE && start.isDiagonalWith(destination)
                && !chessBoard.positionIsEmpty(destination)
                && chessBoard.findPieceByPosition(destination).isOtherTeam(this)) {
            return false;
        }
        return true;
    }
}
