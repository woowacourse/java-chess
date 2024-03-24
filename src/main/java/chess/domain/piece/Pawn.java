package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.BoardDirection;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public static final int KILL_PASSING_DISTANCE = 2;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, ChessBoard chessBoard) {
        if (isNotReachable(start, destination, chessBoard)) {
            return false;
        }
        if (isPathNotClear(start, destination, chessBoard)) {
            return false;
        }
        if (isFriendlyPieceAtDestination(destination, chessBoard)) {
            return false;
        }
        return true;
    }

    boolean isNotReachable(Position start, Position destination, ChessBoard chessBoard) {
        BoardDirection direction = BoardDirection.of(start, destination);
        //팀의 전진방향과 맞도록 위로 한칸 움직이는 것은 가능하다
        if (team.isTeamForwardDirectionsContains(direction) && start.squaredDistanceWith(destination) == 1
                && start.isOrthogonalWith(destination)) {
            return false;
        }
        //폰이 초기위치에 있고 팀의 전진방향과 맞도록 위로 두칸 움직이는 것은 가능 하다
        if (team.isTeamForwardDirectionsContains(direction) && start.squaredDistanceWith(destination) == 4
                && start.isOrthogonalWith(destination)
                && start.isRankSameWith(teamInitialPawnRow())) {
            return false;
        }
        //팀의 전진방향과 맞고 대각선으로 1칸 움직일 때 목적지에 적이 있으면 움직일 수 있다
        if (team.isTeamForwardDirectionsContains(direction) && start.squaredDistanceWith(destination) == 2
                && start.isDiagonalWith(destination) && !chessBoard.positionIsEmpty(destination)
                && chessBoard.findPieceByPosition(destination).isOtherTeam(this)) {
            return false;
        }
        return true;
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
}
