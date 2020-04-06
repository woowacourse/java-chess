package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Position {
    private static List<Position> positions;

    private final File file;
    private final Rank rank;

    private Position(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    static {
        positions = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            addPosition(rank);
        }
    }

    private static void addPosition(Rank rank) {
        for (File file : File.values()) {
            positions.add(new Position(file, rank));
        }
    }

    public static Position of(final String position) {
        File file = File.of(position.substring(0, 1));
        Rank rank = Rank.of(position.substring(1, 2));

        return findPosition(file, rank);
    }

    private static Position findPosition(File file, Rank rank) {
        return positions.stream()
                .filter(p -> p.file.equals(file) && p.rank.equals(rank))
                .findFirst()
                .orElseThrow(AssertionError::new);
    }

    public static Position of(final File file, final Rank rank) {
        return findPosition(file, rank);
    }

    public static Position of(final int fileSymbol, final int rankSymbol) {
        return findPosition(File.of(fileSymbol), Rank.of(rankSymbol));
    }

    public static Position convert(String value) {
        String fileName = value.substring(0, 1);
        String rankName = value.substring(1);
        File file = File.of(fileName);
        Rank rank = Rank.valueOf(rankName);

        return new Position(file, rank);
    }

    public int calculateFileGap(final Position target) {
        return this.file.compareTo(target.file);
    }

    public int calculateRankGap(final Position target) {
        return this.rank.getDifference(target.rank);
    }

    public static List<String> getPositions() {
        List<String> parseResult = positions.stream()
                .map(Position::toString)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(parseResult);
    }

    public static List<Integer> getPositionsIndex() {
        List<Integer> indexes = positions.stream()
                .map(Position::index)
                .collect(Collectors.toList());

        return Collections.unmodifiableList(indexes);
    }

    private int index() {
        return ((this.rank.getSymbol() - 8) * -1)
                + (this.file.getNumber() - 1);
    }

    public int getFile() {
        return this.file.getNumber();
    }

    public int getRank() {
        return this.rank.getSymbol();
    }

    @Override
    public String toString() {
        return file.name() + rank.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return file == position.file &&
                rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }
}
