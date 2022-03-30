package chess.square;

import chess.Direction;
import java.util.ArrayList;
import java.util.List;

public class Square {

    private final Rank rank;
    private final File file;

    private Square(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(Rank rank, File file) {
        return new Square(rank, file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        if (rank != square.rank) {
            return false;
        }
        return file == square.file;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }

    public List<Square> findRoad(Direction direction, int maxDistance) {
        int distance = 1;
        List<Square> roads = new ArrayList<>();
        List<Integer> coordinates = direction.getDistanceFrom(distance);
        while (file.availableLocation(coordinates.get(0)) && rank.availableLocation(coordinates.get(1)) && distance <= maxDistance) {
            roads.add(distanceFrom(coordinates));
            distance++;
            coordinates = direction.getDistanceFrom(distance);
        }
        return roads;
    }

    private Square distanceFrom(List<Integer> coordinates) {
        return Square.of(rank.nextTo(coordinates.get(1)), file.nextTo(coordinates.get(0)));
    }
}
