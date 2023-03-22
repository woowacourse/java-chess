package chess.domain.piece.move;

import java.util.Set;

public class LocationHelper {
    public static Location getBishopLocation() {
        final Location expected = new Location();
        final Set<Position> positions = Set.of(new Position(4, 4), new Position(5, 5),
                new Position(6, 6), new Position(7, 7), new Position(2, 2),
                new Position(1, 1), new Position(0, 0), new Position(4, 2),
                new Position(5, 1), new Position(6, 0), new Position(2, 4),
                new Position(1, 5), new Position(0, 6));
        expected.add(positions);
        return expected;
    }

    public static Location getRookLocation() {
        final Location expected = new Location();
        final Set<Position> positions = Set.of(new Position(3, 4), new Position(3, 5),
                new Position(3, 6), new Position(3, 7), new Position(3, 2),
                new Position(3, 1), new Position(3, 0), new Position(4, 3),
                new Position(5, 3), new Position(6, 3), new Position(7, 3),
                new Position(2, 3), new Position(1, 3), new Position(0, 3));
        expected.add(positions);
        return expected;
    }

    public static Location getQueenLocation() {
        final Location expected = new Location();
        final Set<Position> positions = Set.of(new Position(4, 4), new Position(5, 5),
                new Position(6, 6), new Position(7, 7), new Position(2, 2),
                new Position(1, 1), new Position(0, 0), new Position(4, 2),
                new Position(5, 1), new Position(6, 0), new Position(2, 4),
                new Position(1, 5), new Position(0, 6), new Position(3, 4),
                new Position(3, 5), new Position(3, 6), new Position(3, 7),
                new Position(3, 2), new Position(3, 1), new Position(3, 0),
                new Position(4, 3), new Position(5, 3), new Position(6, 3),
                new Position(7, 3), new Position(2, 3), new Position(1, 3),
                new Position(0, 3));
        expected.add(positions);
        return expected;
    }

    public static Location getKingLocation() {
        final Location expected = new Location();
        final Set<Position> positions = Set.of(new Position(4, 3), new Position(2, 3),
                new Position(3, 2), new Position(3, 4), new Position(4, 4),
                new Position(2, 4), new Position(4, 2), new Position(2, 2));
        expected.add(positions);
        return expected;
    }

    public static Location getWhitePawnLocation() {
        final Location expected = new Location();
        final Set<Position> positions = Set.of(new Position(4, 3), new Position(4, 4),
                new Position(5, 5), new Position(5, 1), new Position(5, 3),
                new Position(4, 2));
        expected.add(positions);
        return expected;
    }

    public static Location getBlackPawnLocation() {
        final Location expected = new Location();
        final Set<Position> positions = Set.of(new Position(2, 2), new Position(1, 1),
                new Position(2, 3), new Position(1, 3), new Position(2, 4),
                new Position(1, 5));
        expected.add(positions);
        return expected;
    }
}
