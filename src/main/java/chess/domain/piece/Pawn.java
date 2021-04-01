package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pawn extends Piece {

    public static final int UP_DIRECTION = 1;
    public static final int DOWN_DIRECTION = -1;
    public static final int LEFT_DIRECTION = -1;
    public static final int RIGHT_DIRECTION = 1;
    private static final int BLACK_PAWN_INITIAL_Y = 7;
    private static final int WHITE_PAWN_INITIAL_Y = 2;
    private static final int INITIAL_PAWN_MOVE_DISTANCE = 2;
    private static final int MINIMUM_X = 1;
    private static final int MAXIMUM_X = 8;

    private Pawn(final Location location, final Team team) {
        super(location, team);
    }

    public static Pawn of(final Location location, final Team team) {
        return new Pawn(location, team);
    }

    public static List<Piece> createInitialPieces() {
        return Stream.concat(
            IntStream.rangeClosed(MINIMUM_X, MAXIMUM_X)
                .mapToObj(x -> of(Location.of(x, WHITE_PAWN_INITIAL_Y), Team.WHITE)),
            IntStream.rangeClosed(MINIMUM_X, MAXIMUM_X)
                .mapToObj(x -> of(Location.of(x, BLACK_PAWN_INITIAL_Y), Team.BLACK))
        ).collect(Collectors.toList());
    }

    private List<Location> makePossibleLocation(final Location target) {
        int dy = UP_DIRECTION;
        if (team.isBlack()) {
            dy = DOWN_DIRECTION;
        }
        List<Location> locations = bringAlwaysMovableLocations(dy);

        addOnlyInitialMovableLocation(dy, locations);
        return locations;
    }

    private List<Location> bringAlwaysMovableLocations(final int dy) {
        List<Location> locations =
            IntStream.rangeClosed(LEFT_DIRECTION, RIGHT_DIRECTION)
                .filter(dx -> location.isRangeByStep(dx, dy))
                .boxed()
                .map(dx -> location.moveByStep(dx, dy))
                .collect(Collectors.toList());
        return locations;
    }

    private void addOnlyInitialMovableLocation(final int dy, final List<Location> locations) {
        if (isInitialLocation() && location.isRangeByStep(0, INITIAL_PAWN_MOVE_DISTANCE * dy)) {
            locations.add(location.moveByStep(0, INITIAL_PAWN_MOVE_DISTANCE * dy));
        }
    }

    private boolean isInitialLocation() {
        return (team.isBlack() && location.isSameY(BLACK_PAWN_INITIAL_Y))
            || (team.isWhite() && location.isSameY(WHITE_PAWN_INITIAL_Y));
    }

    @Override
    public boolean isMovable(final Location target) {
        List<Location> nextLocations = makePossibleLocation(target);

        return nextLocations.stream()
            .anyMatch(location -> location.equals(target));
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }
}
