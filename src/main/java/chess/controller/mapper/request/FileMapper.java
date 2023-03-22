package chess.controller.mapper.request;

import chess.domain.position.File;
import java.util.Arrays;

public enum FileMapper {
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

    FileMapper(File file, String viewFile) {
        this.file = file;
        this.viewFile = viewFile;
    }

    public static File toFile(String viewFile) {
        return Arrays.stream(FileMapper.values())
                .filter(it -> it.viewFile.equalsIgnoreCase(viewFile))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 입니다."))
                .file;
    }
}
