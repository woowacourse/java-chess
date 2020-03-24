package chess;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private static List<Position> positions;

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    static {
        positions = new ArrayList<>();
        for (File file : File.values()) {
            addPosition(file);
        }
    }

    private static void addPosition(File file) {
        for (Rank rank : Rank.values()) {
            positions.add(new Position(file, rank));
        }
    }

    public static Position of(String position) {
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
}
