package chess.domain.path;

import chess.domain.Position;
import java.util.List;

public class MovablePaths {

    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private final List<Path> paths;

    public MovablePaths(final List<Path> paths) {
        this.paths = paths;
    }

    public Path findPathContainingPosition(final Position position) {
        boolean isPathNotFound = true;
        int pathIndex = 0;
        while (pathIndex < paths.size() && (isPathNotFound = !paths.get(pathIndex).hasPosition(position))) {
            pathIndex++;
        }
        if (isPathNotFound) {
            throw new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE);
        }
        return paths.get(pathIndex);
    }

    public int getTotalPositionCount() {
        return paths.stream()
            .mapToInt(Path::size)
            .sum();
    }

}
