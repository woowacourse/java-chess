package chess.domain.piece.pawn;

import chess.domain.board.Board;
import chess.domain.position.Direction;
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

        return hasHindrance(to, board);

    }

    public boolean hasHindrance(Position to, Board board) {
        if (isHeadingDiagonal(to)) {
            return false;
        }

        return hasHindrance(board);
    }

    private boolean isHeadingDiagonal(Position to) {
        return position.isDiagonalDirection(to);
    }

    private boolean hasHindrance(Board board) {
        Position forwardPosition = position;
        for (int i = 0; i < MAX_DISTANCE; i++) {
            forwardPosition = forwardPosition.go(team.getForwardDirection());
            Piece forward = board.getPiece(forwardPosition);
            if (forward.isNotBlank()) {
                return true;
            }
        }
        return false;


    }
}
