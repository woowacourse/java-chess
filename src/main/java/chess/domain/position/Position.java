package chess.domain.position;

import chess.domain.piece.Color;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Position {
    private static final Map<String, Position> CACHE = Arrays.stream(Rank.values())
            .flatMap(rank -> Arrays.stream(File.values()).map(file -> new Position(file, rank)))
            .collect(Collectors.toMap(position -> toKey(position.file, position.rank), Function.identity()));

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return CACHE.get(toKey(file, rank));
    }

    public static Position from(String positionSymbol) {
        String fileValue = positionSymbol.substring(0, 1);
        int rankValue = convertToRankValue(positionSymbol.substring(1, 2));

        File file = File.convertToFile(fileValue);
        Rank rank = Rank.convertToRank(rankValue);

        return new Position(file, rank);
    }

    private static int convertToRankValue(String rankValue) {
        try {
            return Integer.parseInt(rankValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("적절하지 않은 위치입니다.");
        }
    }

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    public boolean findPosition(File file, Rank rank) {
        return file == this.file && rank == this.rank;
    }

    public int calculateFileDifference(Position target) {
        return file.calculateDifference(target.file);
    }

    public int calculateRankDifference(Position target) {
        return rank.calculateDifference(target.rank);
    }

    public Position move(int fileUnit, int rankUnit) {
        File movedFile = file.move(fileUnit);
        Rank movedRank = rank.move(rankUnit);

        return Position.of(movedFile, movedRank);
    }

    public boolean isPawnFirstTry(Color color) {
        if (color == Color.BLACK && rank == Rank.SEVEN) {
            return true;
        }
        return color == Color.WHITE && rank == Rank.TWO;
    }

    public String getFileSymbol() {
        return file.getSymbol();
    }

    public int getRankValue() {
        return rank.getSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
