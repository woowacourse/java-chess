package chess.domain.repository.dto;

import chess.domain.position.File;
import java.util.Arrays;

public enum FileDtoMapper {
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
    private final String value;

    FileDtoMapper(File file, String value) {
        this.file = file;
        this.value = value;
    }

    public static String convertToFileValue(File file) {
        return Arrays.stream(FileDtoMapper.values())
                .filter(it -> it.file == file)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 입니다."))
                .value;
    }
}
