package chess.renderer;

import chess.domain.board.File;

import java.util.Arrays;

public enum FileInputRenderer {
    A("a", File.A),
    B("b", File.B),
    C("c", File.C),
    D("d", File.D),
    E("e", File.E),
    F("f", File.F),
    G("g", File.G),
    H("h", File.H);
    private final String input;
    private final File output;

    FileInputRenderer(String input, File output) {
        this.input = input;
        this.output = output;
    }

    static public File renderString(String input) {
        return Arrays.stream(values())
                .filter(value -> value.input.equals(input))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .output;
    }
}
