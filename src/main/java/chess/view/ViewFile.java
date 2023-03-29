package chess.view;

import chess.domain.position.File;

import java.util.Arrays;

public enum ViewFile {
    A(File.A, "a"),
    B(File.B, "b"),
    C(File.C, "c"),
    D(File.D, "d"),
    E(File.E, "e"),
    F(File.F, "f"),
    G(File.G, "g"),
    H(File.H, "h"),
    ;

    private final File file;
    private final String viewFile;

    ViewFile(File file, String viewFile) {
        this.file = file;
        this.viewFile = viewFile;
    }

    public static File from(final String viewFile) {
        return Arrays.stream(ViewFile.values())
                .filter(it -> it.viewFile.equals(viewFile))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 입니다."))
                .file;
    }
}
