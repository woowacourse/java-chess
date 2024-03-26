package chess.domain.piece;

import chess.domain.position.DirectionJudge;
import chess.domain.position.Position;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;

public class Pawn extends Piece {
    public static final int FORWARDING_SQUARED_DISTANCE = 1;
    public static final int KILL_PASSING_DISTANCE = 2;
    public static final int FIRST_FORWARDING_SQUARED_DISTANCE = 4;

    public Pawn(Team team) {
        super(team);
    }

    @Override
    public boolean canMove(Position start, Position destination, Piece pieceAtDestination) {
        return isForwardPassing(start, destination, pieceAtDestination)
                || isFirstForwardPassing(start, destination)
                || isKillPassing(start, destination, pieceAtDestination);
    }

    private boolean isForwardPassing(Position start, Position destination, Piece pieceAtDestination) {
        return isForward(start, destination)
                && start.squaredDistanceWith(destination) == FORWARDING_SQUARED_DISTANCE
                && pieceAtDestination.isEmpty();
    }

    private boolean isFirstForwardPassing(Position start, Position destination) {
        return isForward(start, destination)
                && start.squaredDistanceWith(destination) == FIRST_FORWARDING_SQUARED_DISTANCE
                && isInitialPawnRow(start);
    }

    private boolean isKillPassing(Position start, Position destination, Piece pieceAtDestination) {
        return isForward(start, destination)
                && start.squaredDistanceWith(destination) == KILL_PASSING_DISTANCE
                && isOtherTeam(pieceAtDestination);
    }

    private boolean isInitialPawnRow(Position start) {
        if (isBlackTeam()) {
            return BLACK.isInitialPawnRow(start);
        }
        return WHITE.isInitialPawnRow(start);
    }

    private boolean isForward(Position start, Position destination) {
        if (isBlackTeam()) {
            return BLACK.isForward(DirectionJudge.judge(start, destination));
        }
        return WHITE.isForward(DirectionJudge.judge(start, destination));
    }
}
