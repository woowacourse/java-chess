package chess.view;

import chess.domain.board.FileCoordinate;

import java.util.Arrays;

import static chess.domain.board.FileCoordinate.INVALID_FILE_COORDINATE_MESSAGE;

public enum FileCoordinateMapper {
    A("a", FileCoordinate.A),
    B("b", FileCoordinate.B),
    C("c", FileCoordinate.C),
    D("d", FileCoordinate.D),
    E("e", FileCoordinate.E),
    F("f", FileCoordinate.F),
    G("g", FileCoordinate.G),
    H("h", FileCoordinate.H),
    ;

    private final String columnView;
    private final FileCoordinate fileCoordinate;

    FileCoordinateMapper(String columnView, FileCoordinate fileCoordinate) {
        this.columnView = columnView;
        this.fileCoordinate = fileCoordinate;
    }

    public static FileCoordinate findBy(String columnView) {
        return Arrays.stream(values())
                .filter(it -> it.columnView.equals(columnView))
                .map(it -> it.fileCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_FILE_COORDINATE_MESSAGE));
    }

    static void validate(String columnView) {
        if (isNotContain(columnView)) {
            throw new IllegalArgumentException(INVALID_FILE_COORDINATE_MESSAGE);
        }
    }

    private static boolean isNotContain(String columnView) {
        return Arrays.stream(values())
                .noneMatch(x -> x.columnView.equals(columnView));
    }
}
