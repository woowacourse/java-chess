package chess.domain.piece;

public enum Unicode {
    KING('\u2654', '\u265A'),
    QUEEN('\u2655', '\u265B'),
    ROOK('\u2656', '\u265C'),
    BISHOP('\u2657', '\u265D'),
    KNIGHT('\u2658', '\u265E'),
    PAWN('\u2659', '\u265F');

    private final char whiteUnicode;
    private final char blackUnicode;

    Unicode(char whiteUnicode, char blackUnicode) {
        this.whiteUnicode = whiteUnicode;
        this.blackUnicode = blackUnicode;
    }

    public char of(Color color) {
        if (Color.BLACK.equals(color)) {
            return blackUnicode;
        }
        return whiteUnicode;
    }
}
