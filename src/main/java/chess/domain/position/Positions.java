package chess.domain.position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Positions {
    private static final List<Position> positions = new ArrayList<>(generate());

    public Positions() {
    }

    public static Position of(Rank rank, File file) {
        return positions.stream()
                .filter(position -> position.findPosition(rank, file))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당되는 포지션이 없습니다."));
    }

    private static List<Position> generate() {
        return Arrays.stream(Rank.values())
                .map(Positions::generatePositionsByFile)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Position> generatePositionsByFile(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Position(rank, file))
                .collect(Collectors.toList());
    }
}
