package chess.domain.board;


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

    public List<Integer> calculateDistance(Position another) {
        int columnDistance = this.file.calculate(another.file);
        int rowDistance = this.rank.calculate(another.rank);

        return List.of(columnDistance, rowDistance);
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

    private List<Position> getVerticalPositions(List<Rank> rankBetween) {
        return rankBetween.stream()
                .map(rank -> Position.of(rank, this.file))
                .collect(Collectors.toList());
    }

    private List<Position> getHorizontalPositions(List<File> fileBetween) {
        return fileBetween.stream()
                .map(file -> Position.of(this.rank, file))
                .collect(Collectors.toList());
    }

    private List<Position> getDiagonalPositions(List<File> fileBetween, List<Rank> rankBetween) {
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < fileBetween.size(); i++) {
            positions.add(Position.of(rankBetween.get(i), fileBetween.get(i)));
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
