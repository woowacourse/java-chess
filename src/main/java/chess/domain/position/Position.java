package chess.domain.position;

import java.util.*;

public class Position {
    private static final Map<String, Position> POSITIONS = new LinkedHashMap<>();

    static {
        Arrays.stream(Rank.values())
                .forEach(rankValue ->
                        Arrays.stream(File.values())
                                .forEach(fileValue ->
                                        POSITIONS.put(rankValue.getRank() + fileValue.getFile(),
                                                new Position(rankValue, fileValue))
                                )
                );
    }

    private final Rank rank;
    private final File file;

    private Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Map<String, Position> getPositions() {
        return Collections.unmodifiableMap(POSITIONS);
    }

    public static Position emptyPosition() {
        return new Position(Rank.EMPTY, File.EMPTY);
    }

    public static Position valueOf(final Rank rank, final File file) {
        return POSITIONS.get(rank.getRank() + file.getFile());
    }

    public static Position valueOf(final String rank, final String file) {
        Rank findRank = Rank.findByRank(rank);
        File findFile = File.findByFile(file);

        return POSITIONS.get(findRank.getRank() + findFile.getFile());
    }

    public static Position valueOf(final int rank, final int file) {
        Rank findRank = Rank.findByValue(rank);
        File findFile = File.findByValue(file);

        return POSITIONS.get(findRank.getRank() + findFile.getFile());
    }

    public static Position find(String source) {
        String reversedSource = reverse(source);
        if (Objects.isNull(POSITIONS.get(reversedSource))) {
            throw new IllegalArgumentException("없는 위치입니다!");
        }
        return POSITIONS.get(reversedSource);
    }

    private static String reverse(String source) {
        String reversedSource = "";
        reversedSource += source.charAt(1);
        reversedSource += source.charAt(0);
        return reversedSource;
    }

    public Rank getRank() {
        return rank;
    }

    public File getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return rank == position.rank && file == position.file;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, file);
    }

    @Override
    public String toString() {
        return file.getFile() + rank.getRank();
    }
}
