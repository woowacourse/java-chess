package chess.view.matcher;

import chess.domain.position.ChessFile;

import java.util.Arrays;

public enum ChessFileMatcher {
    A("a", ChessFile.A),
    B("b", ChessFile.B),
    C("c", ChessFile.C),
    D("d", ChessFile.D),
    E("e", ChessFile.E),
    F("f", ChessFile.F),
    G("g", ChessFile.G),
    H("h", ChessFile.H),
    ;

    private final String text;
    private final ChessFile file;

    ChessFileMatcher(String text, ChessFile file) {
        this.text = text;
        this.file = file;
    }

    public static ChessFile matchByText(final String inputText) {
        return Arrays.stream(values())
                .filter(fileMatcher -> fileMatcher.text.equals(inputText))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("해당 ChessFile을 찾을 수 없습니다."))
                .file;
    }

    public static boolean isPresentFile(final ChessFile file) {
        return Arrays.stream(values())
                .anyMatch(fileMatcher -> fileMatcher.file == file);
    }
}
