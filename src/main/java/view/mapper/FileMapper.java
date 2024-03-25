package view.mapper;

import domain.square.File;

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
                .filter(file -> file.symbol.equals(input))
                .findFirst()
                .map(it -> it.file)
                .orElseThrow(IllegalArgumentException::new);
    }
}
