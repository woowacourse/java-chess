package chess.domain.path;

import chess.domain.position.Position;
import java.util.List;

public class MovablePaths {

    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private final List<Path> paths;

    public MovablePaths(final List<Path> paths) {
        this.paths = paths;
    }

    public Path findPathContainingPosition(final Position position) {
        return paths.stream()
            .filter(path -> path.hasPosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE));
    }

    public int getTotalPositionCount() {
        return paths.stream()
            .mapToInt(Path::size)
            .sum();
    }

}
