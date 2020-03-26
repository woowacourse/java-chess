package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import chess.domain.piece.Direction;

public class Position implements Comparable<Position> {

    private static final Map<String, Position> positions;

    static {
        positions = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                String name = nameOf(file, rank);
                positions.put(name, new Position(file, rank));
            }
        }
    }

    private final File file;
    private final Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(File file, Rank rank) {
        return from(nameOf(file, rank));
    }

    public static Position from(String name) {
        Position position = positions.get(name.toUpperCase());
        validate(name, position);
        return position;
    }

    private static void validate(String name, Position position) {
        if (Objects.isNull(position)) {
            throw new IllegalArgumentException(String.format("%s는 잘못된 입력입니다.", name));
        }
    }

    private static String nameOf(File file, Rank rank) {
        return file.getName() + rank.getName();
    }

    public static List<Position> list() {
        return Collections.unmodifiableList(new ArrayList<>(positions.values()));
    }

    public Position opposite() {
        return of(file.opposite(), rank.opposite());
    }

    public Position horizontalFlip() {
        return of(file, rank.opposite());
    }

    public Optional<Position> destinationOf(Direction direction) {
        File fileDestination = direction.findFileDestination(file).orElse(null);
        Rank rankDestination = direction.findRankDestination(rank).orElse(null);

        if (fileDestination == null || rankDestination == null) {
            return Optional.empty();
        }

        return Optional.of(of(fileDestination, rankDestination));
    }

    @Override
    public int compareTo(Position position) {
        if (rank.compareTo(position.rank) == 0) {
            return file.compareTo(position.file);
        }
        return -rank.compareTo(position.rank);
    }
}
