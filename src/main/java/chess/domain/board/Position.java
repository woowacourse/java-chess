package chess.domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Position {
    private static final int ZERO_FOR_NO_CHANGE = 0;
    private static final int BEGIN_INDEX = 0;
    private static final int FILE_INDEX = 1;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position from(String input) {
        return new Position(File.from(input.substring(BEGIN_INDEX, FILE_INDEX)),
                Rank.from(input.substring(FILE_INDEX)));
    }

    public static Position of(File file, Rank rank) {
        return new Position(file, rank);
    }

    public int dx(Position another) {
        return file.dx(another.file);
    }

    public int dy(Position another) {
        return rank.dy(another.rank);
    }

    public List<Position> between(Position to) {
        List<File> fileBetween = file.between(to.file);
        List<Rank> rankBetween = rank.between(to.rank);

        if (isDiagonal(to)) {
            return diagonalBetweens(fileBetween, rankBetween);
        }
        if (isHorizontal(to)) {
            return horizontalBetweens(this, fileBetween);
        }
        return verticalBetweens(this, rankBetween);
    }

    private boolean isDiagonal(Position to) {
        return Math.abs(this.dx(to)) == Math.abs(this.dy(to));
    }

    private List<Position> diagonalBetweens(List<File> fileBetween, List<Rank> rankBetween) {
        List<Position> betweens = new ArrayList<>();
        for (int i = ZERO_FOR_NO_CHANGE; i < rankBetween.size(); i++) {
            betweens.add(Position.of(fileBetween.get(i), rankBetween.get(i)));
        }
        return betweens;
    }

    private boolean isHorizontal(Position to) {
        return this.dx(to) != ZERO_FOR_NO_CHANGE && this.dy(to) == ZERO_FOR_NO_CHANGE;
    }

    private List<Position> horizontalBetweens(Position from, List<File> fileBetween) {
        Rank rank = Rank.from(from);

        return fileBetween.stream()
                .map(file -> Position.of(file, rank))
                .collect(Collectors.toList());
    }

    private List<Position> verticalBetweens(Position from, List<Rank> rankBetween) {
        File file = File.from(from);

        return rankBetween.stream()
                .map(rank -> Position.of(file, rank))
                .collect(Collectors.toList());
    }

    public boolean isSameFile(File file) {
        return this.file == file;
    }

    public boolean isSameRank(Rank rank) {
        return this.rank == rank;
    }

    public String getName() {
        return file.getFileName() + rank.getRankNumber();
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
