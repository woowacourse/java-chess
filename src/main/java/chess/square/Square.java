package chess.square;

import chess.Direction;
import java.util.ArrayList;
import java.util.List;

public class Square {

    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(File file, Rank rank) {
        return new Square(file, rank);
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
        while (isValidLocation(coordinates) && distance <= maxDistance) {
            roads.add(distanceFrom(coordinates));
            distance++;
            coordinates = direction.getDistanceFrom(distance);
        }
        return roads;
    }

    public boolean findLocation(Direction direction, Square target) {
        List<Integer> coordinates = direction.getDistanceFrom(1);
        if (isValidLocation(coordinates)) {
            return distanceFrom(coordinates).equals(target);
        }
        return false;
    }

    private Square distanceFrom(List<Integer> coordinates) {
        return Square.of(file.nextTo(coordinates.get(0)), rank.nextTo(coordinates.get(1)));
    }

    private boolean isValidLocation(List<Integer> coordinates) {
        return file.availableLocation(coordinates.get(0)) && rank.availableLocation(coordinates.get(1));
    }
}
