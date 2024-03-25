package chess.dto;

import chess.model.board.Board;
import java.util.List;

public record BoardDTO(List<LineDTO> board) {
    public static BoardDTO from(Board board) {
        return new BoardDTO(board.getLines()
                .stream()
                .map(LineDTO::from)
                .toList());
    }
}
