package chess.dto;

import chess.model.board.Board;
import java.util.List;

public record BoardDTO(List<LineDTO> board) {
    public BoardDTO(Board board) {
        this(board.getLines()
                .stream()
                .map(LineDTO::new)
                .toList());
    }
}
