package chess.renderer;

import chess.domain.board.File;

import java.util.Arrays;

public enum FileRenderer {
    A("a", File.A),
    B("b", File.B),
    C("c", File.C),
    D("d", File.D),
    E("e", File.E),
    F("f", File.F),
    G("g", File.G),
    H("h", File.H);
    private final String string;
    private final File file;

    FileRenderer(final String string, final File file) {
        this.string = string;
        this.file = file;
    }

    static public File renderString(final String input) {
        return Arrays.stream(values())
                .filter(value -> value.string.equals(input))
                .findAny()
                .orElseThrow(IllegalArgumentException::new)
                .file;
    }

    static public String renderFile(final File file) {
        return Arrays.stream(values())
                .filter(value -> value.file.equals(file))
                .findAny()
                .orElseThrow(IllegalAccessError::new)
                .string;
    }
}
