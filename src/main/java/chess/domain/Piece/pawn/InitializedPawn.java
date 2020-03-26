package chess.domain.Piece.pawn;

import chess.domain.Board;
import chess.domain.Piece.Distance;
import chess.domain.Piece.Piece;
import chess.domain.Piece.state.Initialized;
import chess.domain.Piece.team.Team;
import chess.domain.position.Position;

//todo: add tests
public class InitializedPawn extends Initialized {
    private static final int MAX_DISTANCE = 2;

    protected InitializedPawn(Position position, Team team) {
        super(position, team);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            //todo: change error message
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }

        return new RunningPawn(to, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        if (position.equals(to)) {
            return true;
        }

        if (to.isNotForward(position, team.getForwardDirection())) {
            return true;
        }

        Piece exPiece = board.getPiece(to);
        if (isNotSameTeam(exPiece)) {
            return true;
        }

        Distance distance = position.calculateDistance(to);
        if (distance.isBiggerThan(MAX_DISTANCE)) {
            return true;
        }

        if (isSameTeam(exPiece)) {
            return true;
        }

        return hasHindrance(to, board);

    }

    private boolean hasHindrance(Position to, Board board) {
        if (position.isDiagonalDirection(to)) {
            return false;
        }

        Position forward = position.go(team.getForwardDirection());
        Piece piece = board.getPiece(forward);
        if (forward.equals(to) && piece.isBlank()) {
            return false;
        }

        forward = forward.go(team.getForwardDirection());
        piece = board.getPiece(forward);
        return piece.isNotBlank();
    }
}
