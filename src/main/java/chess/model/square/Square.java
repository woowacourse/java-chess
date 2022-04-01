package chess.model.square;

import chess.model.piece.Color;
import java.util.ArrayList;
import java.util.List;

public class Square {

    private static final int ROW_INDEX = 0;
    private static final int COL_INDEX = 1;
    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        return new Square(file, rank);
    }

    public static Square fromString(String square) {
        File file = File.findFile(Character.toString(square.charAt(ROW_INDEX)));
        Rank rank = Rank.findRank(Integer.parseInt(String.valueOf(square.charAt(COL_INDEX))));
        return new Square(file, rank);
    }

    public List<Square> findRoad(Direction direction, int maxDistance) {
        int distance = COL_INDEX;
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
        List<Integer> coordinates = direction.getDistanceFrom(COL_INDEX);
        if (isValidLocation(coordinates)) {
            return distanceFrom(coordinates).equals(target);
        }
        return false;
    }

    private Square distanceFrom(List<Integer> coordinates) {
        return Square.of(file.nextTo(coordinates.get(ROW_INDEX)), rank.nextTo(coordinates.get(COL_INDEX)));
    }

    private boolean isValidLocation(List<Integer> coordinates) {
        return file.availableLocation(coordinates.get(ROW_INDEX)) && rank.availableLocation(coordinates.get(COL_INDEX));
    }

    public boolean isPawnOnFirstLine(Color color) {
        if (color.isBlack()) {
            return rank.equals(Rank.SEVEN);
        }
        return rank.equals(Rank.TWO);
    }

    public boolean sameFile(File file) {
        return this.file.equals(file);
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
        int result = rank != null ? rank.hashCode() : ROW_INDEX;
        result = 31 * result + (file != null ? file.hashCode() : ROW_INDEX);
        return result;
    }
}
