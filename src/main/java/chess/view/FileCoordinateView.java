package chess.view;

import chess.domain.position.FileCoordinate;
import java.util.Arrays;

public enum FileCoordinateView {
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

    FileCoordinateView(String columnView, FileCoordinate fileCoordinate) {
        this.columnView = columnView;
        this.fileCoordinate = fileCoordinate;
    }

    public static FileCoordinate findBy(String columnView) {
        return Arrays.stream(values())
                .filter(it -> it.columnView.equals(columnView))
                .map(it -> it.fileCoordinate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 열 번호를 입력해주세요."));
    }
}
