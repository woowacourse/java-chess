package techcourse.fp.chess.domain.movingStrategy;

import java.util.ArrayList;
import java.util.List;
import techcourse.fp.chess.domain.Direction;
import techcourse.fp.chess.domain.Directions;
import techcourse.fp.chess.domain.File;
import techcourse.fp.chess.domain.Position;
import techcourse.fp.chess.domain.Rank;

public class SlidingStrategy extends MovingStrategy {

    public SlidingStrategy(final Directions directions) {
        super(directions);
    }

    @Override
    public List<Position> createPath(final Position source, final Position target) {

        final Direction findDirection = directions.findReachableDirection(source, target);
        return getPath(source, target, findDirection);
    }


    private List<Position> getPath(final Position source, final Position target, final Direction findDirection) {
        List<Position> path = new ArrayList<>();

        Position currentPosition = move(source, findDirection);

        while (!currentPosition.equals(target)) {
            path.add(currentPosition);
            currentPosition = move(currentPosition, findDirection);
        }

        return path;
    }

    private Position move(final Position currentPosition, final Direction direction) {
        return Position.of(File.of(currentPosition.getFileOrder() + direction.getFile()),
                Rank.of(currentPosition.getRankOrder() + direction.getRank()));
    }
}
