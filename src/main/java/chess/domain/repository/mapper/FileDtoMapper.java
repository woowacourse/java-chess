package chess.domain.repository.mapper;

import chess.domain.position.File;
import java.util.Arrays;

public enum FileDtoMapper {
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

    public static File convertToFile(String fileValue) {
        return Arrays.stream(FileDtoMapper.values())
                .filter(it -> it.value.equalsIgnoreCase(fileValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 파일 입니다."))
                .file;
    }
}
