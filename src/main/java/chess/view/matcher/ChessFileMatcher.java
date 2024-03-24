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

    public static ChessFile matchByInputText(final String inputText) {
        return Arrays.stream(values())
                .filter(file -> file.text.equals(inputText))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("%s에 해당하는 ChessFile을 찾을 수 없습니다.", inputText)))
                .file;
    }
}
