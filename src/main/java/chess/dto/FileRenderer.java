package chess.dto;

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

    private final String command;
    private final File file;

    FileRenderer(String command, File rank) {
        this.command = command;
        this.file = rank;
    }

    static public File render(String input) {
        return Arrays.stream(values())
                .filter(value -> value.command.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File입니다."))
                .file;
    }

    static public String render(File file) {
        return Arrays.stream(values())
                .filter(value -> value.file == file)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 File입니다."))
                .command;
    }
}
