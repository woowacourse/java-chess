package chess.domain.dto;

import chess.domain.board.Board;
import chess.utils.BoardUtil;

public class BoardDto {

    private final char[][] value;

    private BoardDto(char[][] value) {
        this.value = value;
    }

    public static BoardDto from(Board board) {
        return new BoardDto(BoardUtil.generateViewBoard(board));
    }

    public char[][] getValue() {
        return value;
    }
}
