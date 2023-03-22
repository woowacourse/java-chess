package domain.piece;

import domain.position.Direction;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;

import java.util.List;

public final class Pawn extends Piece {

    private static final String NAME = "P";
    private static final int TWO_STEP = 2;
    private static final Rank INITIAL_RANK_BLACK = Rank.SEVEN;
    private static final Rank INITIAL_RANK_WHITE = Rank.TWO;

    public Pawn(final Team team) {
        super(NAME, team);
    }

    @Override
    public List<Position> getInitialBlackPositions() {
        return List.of(Position.of(File.A, Rank.SEVEN),
                Position.of(File.B, Rank.SEVEN),
                Position.of(File.C, Rank.SEVEN),
                Position.of(File.D, Rank.SEVEN),
                Position.of(File.E, Rank.SEVEN),
                Position.of(File.F, Rank.SEVEN),
                Position.of(File.G, Rank.SEVEN),
                Position.of(File.H, Rank.SEVEN));
    }

    @Override
    public List<Position> getInitialWhitePositions() {
        return List.of(Position.of(File.A, Rank.TWO),
                Position.of(File.B, Rank.TWO),
                Position.of(File.C, Rank.TWO),
                Position.of(File.D, Rank.TWO),
                Position.of(File.E, Rank.TWO),
                Position.of(File.F, Rank.TWO),
                Position.of(File.G, Rank.TWO),
                Position.of(File.H, Rank.TWO));
    }

    @Override
    public boolean isMovable(final Position source, final Position destination) {
        final Direction direction = Direction.of(source, destination);
        final int distance = source.getDistance(destination);

        if (isBlack() && isBlackInitialRank(source)) {
            return Direction.S.equals(direction) && distance <= TWO_STEP;
        }
        if (isBlack()) {
            return source.move(Direction.S).equals(destination);
        }
        if (isWhiteInitialRank(source)) {
            return Direction.N.equals(direction) && distance <= TWO_STEP;
        }
        return source.move(Direction.N).equals(destination);
    }

    private boolean isWhiteInitialRank(final Position source) {
        return INITIAL_RANK_WHITE.equals(source.getRank());
    }

    private boolean isBlackInitialRank(final Position source) {
        return INITIAL_RANK_BLACK.equals(source.getRank());
    }

    @Override
    public boolean isCapturable(final Position source, final Position destination) {
        if (isBlack() &&
                source.move(Direction.SW).equals(destination) ||
                source.move(Direction.SE).equals(destination)) {
            return true;
        }

        return !isBlack() &&
                source.move(Direction.NW).equals(destination) ||
                source.move(Direction.NE).equals(destination);
    }
}
