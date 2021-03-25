package chess.domain.board;

public enum ChessBoardInfo {
    MAX_NUM_PIECE(16),
    ROW_WHITE_START(1),
    ROW_WHITE_END(2),
    COLUMN_FIRST('a'),
    COLUMN_LAST('h'),
    ROW_FIRST(1),
    ROW_LAST(8),
    ROW_BLACK_START(7),
    ROW_BLACK_END(8),
    ROW_WHITE_PAWN_LINE(2),
    ROW_BLACK_PAWN_LINE(7),
    CHESS_BOARD_ROW_FIRST(7),
    CHESS_BOARD_ROW_LAST(0),
    CHESS_BOARD_COLUMN_FIRST(0),
    CHESS_BOARD_COLUMN_LAST(8);

    private final int boardInfo;

    ChessBoardInfo(int boardInfo) {
        this.boardInfo = boardInfo;
    }

    public int getBoardInfo() {
        return boardInfo;
    }

}
