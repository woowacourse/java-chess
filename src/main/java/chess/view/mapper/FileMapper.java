package chess.view.mapper;

import chess.domain.position.File;

import java.util.Arrays;

public enum FileMapper {

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
    private final String symbol;

    FileMapper(File file, String symbol) {
        this.file = file;
        this.symbol = symbol;
    }

    public static File from(String input) {
        return Arrays.stream(values())
                .filter(it -> it.symbol.equals(input))
                .findFirst()
                .map(it -> it.file)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가로 위치입니다."));
    }
}
