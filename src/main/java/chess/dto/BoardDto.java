package chess.dto;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;

public class BoardDto {
    private final Map<String, String> board;

    public BoardDto(Board board) {
        this.board = board.getBoard().entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getName(), entry -> entry.getValue().getEmoji()));
    }

    public Map<String, String> getBoard() {
        return board;
    }
}
