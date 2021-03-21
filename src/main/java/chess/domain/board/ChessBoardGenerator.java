package chess.domain.board;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoardGenerator {

    private ChessBoardGenerator() {
    }

    public static Map<Coordinate, Cell> generateEmptyBoard() {
        return Arrays.stream(File.values())
                .flatMap(ChessBoardGenerator::generateDefaultCoordinate)
                .collect(Collectors.toMap(coordinate -> coordinate, value -> new Cell()));
    }

    private static Stream<Coordinate> generateDefaultCoordinate(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Coordinate(file, rank));
    }
}
