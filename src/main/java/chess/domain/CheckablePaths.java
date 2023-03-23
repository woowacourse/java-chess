package chess.domain;

import java.util.List;

public class CheckablePaths {

    private static final String WRONG_DESTINATION_ERROR_MESSAGE = "해당 말이 갈 수 없는 위치입니다.";
    private final List<Path> paths;

    public CheckablePaths(final List<Path> paths) {
        this.paths = paths;
    }

    public Path findPathContainingPosition(final Position position) {
        return paths.stream()
                .filter(path -> path.hasPosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(WRONG_DESTINATION_ERROR_MESSAGE));
    }

    public int positionsSize() {
        return paths.stream()
                .mapToInt(Path::size)
                .sum();
    }
}
