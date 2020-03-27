package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.position.Distance;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Initialized;
import chess.domain.piece.team.Team;
import chess.domain.position.Position;

//todo: add tests
public class InitializedPawn extends Initialized {
    private static final int MAX_DISTANCE = 2;

    public InitializedPawn(String name, Position position, Team team) {
        super(name, position, team);
    }

    @Override
    public Piece move(Position to, Board board) {
        if (canNotMove(to, board)) {
            //todo: change error message
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }

        return new RunningPawn(name, to, team);
    }

    @Override
    protected boolean canNotMove(Position to, Board board) {
        if (position.equals(to)) {
            return true;
        }

        if (to.isBackward(position, team.getForwardDirection())) {
            return true;
        }

        Piece exPiece = board.getPiece(to);
        if (isSameTeam(exPiece)) {
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
