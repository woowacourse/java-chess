package chess.view.dto;

import chess.domain.Position;
import java.util.Map;

public final class BoardDto {

    private final Map<Position, String> board;
    private final int boardSize;

    public BoardDto(Map<Position, String> board, int boardSize) {
        this.board = board;
        this.boardSize = boardSize;
    }

    public Map<Position, String> board() {
        return board;
    }

    public int boardSize() {
        return boardSize;
    }
}