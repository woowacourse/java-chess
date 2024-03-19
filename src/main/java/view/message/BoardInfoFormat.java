package view.message;

import model.BoardInfo;

public enum BoardInfoFormat {

    KING(BoardInfo.KING, "k"),
    BISHOP(BoardInfo.BISHOP, "b"),
    KNIGHT(BoardInfo.KNIGHT, "n"),
    PAWN(BoardInfo.PAWN, "p"),
    QUEEN(BoardInfo.QUEEN, "q"),
    ROOK(BoardInfo.ROOK, "r"),
    BLANK(BoardInfo.BLANK, ".")
    ;

    private final BoardInfo boardInfo;
    private final String value;

    BoardInfoFormat(BoardInfo boardInfo, String value) {
        this.boardInfo = boardInfo;
        this.value = value;
    }

    public static BoardInfoFormat from(BoardInfo boardInfo) {
        for (BoardInfoFormat boardInfoFormat : values()) {
            if (boardInfo == boardInfoFormat.boardInfo) {
                return boardInfoFormat;
            }
        }
        throw new IllegalArgumentException("값 없음");
    }

    public String getValue() {
        return value;
    }
}
