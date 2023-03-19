package chess.view.input;

import chess.domain.position.File;
import java.util.Arrays;

public enum ViewFile {
    A(File.A),
    B(File.B),
    C(File.C),
    D(File.D),
    E(File.E),
    F(File.F),
    G(File.G),
    H(File.H),
    ;

    private final File file;

    ViewFile(File file) {
        this.file = file;
    }

    public static File from(String viewFile) {
        return Arrays.stream(ViewFile.values())
                .filter(it -> it.file == File.valueOf(viewFile.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 입니다."))
                .file;
    }
}
