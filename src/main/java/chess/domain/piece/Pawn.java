package chess.domain.piece;

import chess.domain.position.Position;

public sealed abstract class Pawn extends Piece
        permits BlackPawn, WhitePawn {
    protected static final int FORWARDING_SQUARED_DISTANCE = 1;
    protected static final int KILL_PASSING_DISTANCE = 2;
    protected static final int FIRST_FORWARDING_SQUARED_DISTANCE = 4;

    public Pawn(Team team) {
        super(team);
    }

    public static Pawn blackPawn() {
        return new BlackPawn();
    }

    public static Pawn whitePawn() {
        return new WhitePawn();
    }


    abstract boolean isInitialPawnRow(Position start);

    abstract boolean isForward(Position start, Position destination);

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
}
