package chess.domain.position;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Square {

    private static final Map<String, Square> pool = Arrays.stream(Rank.values())
            .flatMap(rank -> Arrays.stream(File.values()).map(file -> new Square(file, rank)))
            .collect(Collectors.toMap(it -> toKey(it.file, it.rank), Function.identity()));

    private final File file;
    private final Rank rank;

    private static String toKey(File file, Rank rank) {
        return file.name() + rank.name();
    }

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        return pool.get(toKey(file, rank));
    }

    public Square forward(int index) {
        return Square.of(file, rank.forward(index));
    }
}
