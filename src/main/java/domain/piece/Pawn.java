package domain.piece;

import domain.position.Position;
import domain.position.Positions;
import domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int ONE_STEP = 1;
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static final int UP = 1;
    private static final int DOWN = -1;
    private static final int TWO_STEP_AT_FIRST = 2;
    private static final char INITIAL_RANK_BLACK = '7';
    private static final char INITIAL_RANK_WHITE = '2';

    public Pawn(Team team) {
        super(NAME, team);
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        if (nowInitialPosition(source)) {
            return firstMovable(source, destination);
        }
        if (isBlack()) {
            return source.moveDown(ONE_STEP).equals(destination);
        }
        return source.moveUp(ONE_STEP).equals(destination);
    }

    private boolean firstMovable(final Position source, final Position destination) {
        if (isBlack()) {
            return source.moveDown(TWO_STEP_AT_FIRST).equals(destination) ||
                    source.moveDown(ONE_STEP).equals(destination);
        }
        return source.moveUp(TWO_STEP_AT_FIRST).equals(destination) ||
                source.moveUp(ONE_STEP).equals(destination);
    }

    private boolean nowInitialPosition(final Position source) {
        return (isBlack() && source.getRank() == INITIAL_RANK_BLACK) ||
                (isWhite() && source.getRank() == INITIAL_RANK_WHITE);
    }

    @Override
    public boolean isEatable(final Position source, final Position destination) {
        if (isBlack() &&
                Objects.equals(destination, validateEdgeDestination(source, DOWN, LEFT)) ||
                Objects.equals(destination, validateEdgeDestination(source, DOWN, RIGHT))) {
            return true;
        }

        return !isBlack() &&
                Objects.equals(destination, validateEdgeDestination(source, UP, LEFT)) ||
                Objects.equals(destination, validateEdgeDestination(source, UP, RIGHT));
    }

    @Override
    public Score getScore(final Position source, final Map<Position, Piece> influentialPieceForScore) {
        List<Position> positions = influencingPositions(source);
        if (isAllyPawnInColumn(positions, influentialPieceForScore)) {
            return new Score(0.5);
        }
        return new Score(1);
    }

    private boolean isAllyPawnInColumn(List<Position> influencingPositions,
                                       final Map<Position, Piece> influentialPieces) {
        return influencingPositions.stream().filter(influentialPieces::containsKey)
                .anyMatch(position -> influentialPieces.get(position).getClass().equals(this.getClass()));
    }

    private List<Position> influencingPositions(final Position source) {
        char file = (char) source.getFile();
        return Arrays.stream(Rank.values())
                .map(rank -> Positions.from(file + rank.getName()))
                .filter(position -> !source.equals(position))
                .collect(Collectors.toList());
    }

    private Position validateEdgeDestination(final Position source, final int rank, final int file) {
        try {
            return source.move(rank, file);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
