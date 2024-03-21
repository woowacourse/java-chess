package chess.domain.position;

import chess.view.FileSymbol;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Positions {
    private static final List<Position> positions = new ArrayList<>(generate());

    public Positions() {
    }

    public static Position of(File file, Rank rank) {
        return positions.stream()
                .filter(position -> position.findPosition(file, rank))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당되는 포지션이 없습니다."));
    }

    public static Position findByInput(String positionSymbol) {
        String fileValue = positionSymbol.substring(0, 1);
        String rankValue = positionSymbol.substring(1, 2);

        File file = File.convertToFile(FileSymbol.convertToFileSymbol(fileValue));
        Rank rank = Rank.convertToRank(rankValue);

        return Positions.of(file, rank);
    }

    private static List<Position> generate() {
        return Arrays.stream(Rank.values())
                .map(Positions::generatePositionsByFile)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    private static List<Position> generatePositionsByFile(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Position(file, rank))
                .collect(Collectors.toList());
    }
}
