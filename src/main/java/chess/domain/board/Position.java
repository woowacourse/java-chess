package chess.domain.board;


import chess.domain.Distance;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
    private static final int FROM_FOR_ROW = 0;
    private static final int TO_FOR_ROW = 1;
    private static final int START_INDEX_FOR_COLUMN = 1;

    private final Rank rank;
    private final File file;

    private Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Position of(String input) {
        return new Position(
                Rank.of(input.substring(START_INDEX_FOR_COLUMN)),
                File.from(input.substring(FROM_FOR_ROW, TO_FOR_ROW)));
    }

    public static Position of(Rank rank, File file) {
        return new Position(rank, file);
    }

    public Distance calculateDistance(Position another) {
        int fileDistance = this.file.calculateDistance(another.file);
        int rankDistance = this.rank.calculateDistance(another.rank);

        return new Distance(fileDistance, rankDistance);
    }

    public List<Position> getPositionBetween(Position target) {
        List<File> fileBetween = File.getBetween(this.file, target.file);
        List<Rank> rankBetween = Rank.getBetween(this.rank, target.rank);

        if (fileBetween.isEmpty()) {
            return getVerticalPositions(rankBetween);
        }

        if (rankBetween.isEmpty()) {
            return getHorizontalPositions(fileBetween);
        }

        return getDiagonalPositions(fileBetween, rankBetween);
    }

    private List<Position> getVerticalPositions(List<Rank> ranksBetween) {
        return ranksBetween.stream()
                .map(rank -> Position.of(rank, this.file))
                .collect(Collectors.toList());
    }

    private List<Position> getHorizontalPositions(List<File> filesBetween) {
        return filesBetween.stream()
                .map(file -> Position.of(this.rank, file))
                .collect(Collectors.toList());
    }

    private List<Position> getDiagonalPositions(List<File> filesBetween, List<Rank> ranksBetween) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < filesBetween.size(); i++) {
            positions.add(Position.of(ranksBetween.get(i), filesBetween.get(i)));
        }

        return positions;
    }

    public boolean isSameFile(File file) {
        return this.file == file;
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
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }
}
