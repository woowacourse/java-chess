package domain.piece.fixture;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PositionFixture { // TODO: PositionGenerator 와 일부 중복 & 이 클래스에 대한 테스트 만들기

    private static final Map<File, Map<Rank, Position>> POSITIONS = new HashMap<>();

    static {
        for (File file : File.values()) {
            Map<Rank, Position> rankPosition = rankPosition(file);
            POSITIONS.put(file, rankPosition);
        }
    }

    private static Map<Rank, Position> rankPosition(File file) {
        Map<Rank, Position> rankPosition = new HashMap<>();
        for (Rank rank : Rank.values()) {
            Position position = new Position(file, rank);
            rankPosition.put(rank, position);
        }
        return rankPosition;
    }

    public static Position get(File file, Rank rank) {
        return POSITIONS.get(file).get(rank);
    }

    public static List<Position> otherPositions(Position position) {
        List<Position> allPositions = allPositions();
        allPositions.remove(position);
        return allPositions;
    }

    public static List<Position> otherPositions(List<Position> positions) {
        List<Position> allPositions = allPositions();
        allPositions.removeAll(positions);
        return allPositions;
    }

    private static List<Position> allPositions() {
        return POSITIONS.values().stream()
                .flatMap(map -> map.values().stream())
                .collect(Collectors.toList());
    }
}
