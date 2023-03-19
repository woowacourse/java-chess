package chess.view.input;

import chess.domain.position.File;
import java.util.Arrays;

public enum ViewFile {
    A(File.A, "A"),
    B(File.B, "B"),
    C(File.C, "C"),
    D(File.D, "D"),
    E(File.E, "E"),
    F(File.F, "F"),
    G(File.G, "G"),
    H(File.H, "H"),
    ;

    private final File file;
    private final String viewFile;

    ViewFile(File file, String viewFile) {
        this.file = file;
        this.viewFile = viewFile;
    }

    public static File from(String viewFile) {
        return Arrays.stream(ViewFile.values())
                .filter(it -> it.viewFile.equals(viewFile))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 입니다."))
                .file;
    }
}
