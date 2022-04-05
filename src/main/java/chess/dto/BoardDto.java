package chess.dto;

import java.util.List;
import java.util.stream.Collectors;

import chess.domain.board.Board;

public class BoardDto {
    private final List<PieceDto> pieces;

    public BoardDto(Board board) {
        this.pieces = board.getBoard().values().stream()
                .map(PieceDto::new)
                .collect(Collectors.toList());
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
