package chess.view;

import chess.domain.position.File;
import java.util.Arrays;
import java.util.Objects;

public enum FileView {
    A("a", File.A),
    B("b", File.B),
    C("c", File.C),
    D("d", File.D),
    E("e", File.E),
    F("f", File.F),
    G("g", File.G),
    H("h", File.H);

    private final String viewName;
    private final File file;

    FileView(final String viewName, final File file) {
        this.viewName = viewName;
        this.file = file;
    }

    public static File find(String viewName) {
        return Arrays.stream(FileView.values())
                .filter(fileView -> Objects.equals(fileView.viewName, viewName))
                .findAny()
                .map(fileView -> fileView.file)
                .orElseThrow(() -> new IllegalArgumentException("입력 값이 file에 준비되어 있지 않습니다."));
    }
}
