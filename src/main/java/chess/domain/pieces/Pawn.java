package chess.domain.pieces;

import chess.domain.position.Position;
import chess.domain.Team;

import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends Piece {
    private static final double SCORE = 1;
    private static final int DISTANCE_FOR_FORWARD_DIRECTION = 1;
    private static final int DISTANCE_FOR_DIAGONALLY_DIRECTION = 2;
    private static final int DISTANCE_FOR_STARTING_POSITION = 4;
    private static final int STANDARD_DIRECTION_VALUE = 0;

    public Pawn(Position position, Team team) {
        super(position, team);
    }

    public static double getSameColumnSamePawnCount(List<Pawn> pawns) {
        List<Position> pawnPositions = pawns.stream()
                .map(pawn -> pawn.position)
                .collect(Collectors.toList());

        return Position.getDuplicatedItemsCount(pawnPositions);
    }

    @Override
    public boolean canMove(Position position) {
        return isPossibleDistance(position) && isPossibleDirection(position);
    }

    private boolean isPossibleDistance(Position position) {
        return this.position.getDistanceSquare(position) == DISTANCE_FOR_FORWARD_DIRECTION
                || this.position.getDistanceSquare(position) == DISTANCE_FOR_DIAGONALLY_DIRECTION
                || isPossibleDistanceFromStartingPosition(position);
    }

    private boolean isPossibleDistanceFromStartingPosition(Position position) {
        return this.position.isInStartingPosition()
                && (this.position.getDistanceSquare(position) == DISTANCE_FOR_STARTING_POSITION);
    }

    private boolean isPossibleDirection(Position position) {
        return (team.equals(Team.WHITE) && this.position.subtractY(position) < STANDARD_DIRECTION_VALUE)
                || (team.equals(Team.BLACK) && this.position.subtractY(position) > STANDARD_DIRECTION_VALUE);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String getSymbol() {
        return "p";
    }

    @Override
    public String toString() {
        return "PAWN";
    }
}
