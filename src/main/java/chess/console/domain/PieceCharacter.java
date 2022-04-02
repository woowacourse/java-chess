package chess.console.domain;

import java.util.Arrays;

public enum PieceCharacter {

    KING("King", "K"),
    QUEEN("Queen", "Q"),
    BISHOP("Bishop", "B"),
    ROOK("Rook", "R"),
    KNIGHT("Knight", "N"),
    PAWN("Pawn", "P"),
    ;

    private final String pieceName;
    private final String outputCharacter;

    PieceCharacter(final String pieceName, final String outputCharacter) {
        this.pieceName = pieceName;
        this.outputCharacter = outputCharacter;
    }

    public static String convertToOutputCharacter(final String pieceName) {
        return Arrays.stream(values())
                .filter(it -> it.equalsPieceName(pieceName))
                .map(it -> it.outputCharacter)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("기물이름을 출력문자로 변환할 수 없습니다."));
    }

    private boolean equalsPieceName(final String pieceName) {
        return pieceName.equalsIgnoreCase(this.pieceName);
    }
}
