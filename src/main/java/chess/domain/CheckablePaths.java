package chess.domain;

import java.util.List;

public class CheckablePaths {

    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private final List<Path> paths;

    public CheckablePaths(final List<Path> paths) {
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

    public int positionsSize() {
        return paths.stream()
                .mapToInt(Path::size)
                .sum();
    }
}
