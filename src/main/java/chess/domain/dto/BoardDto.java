package chess.domain.dto;

import java.util.List;

public class BoardDto {
    private final List<List<PieceDto>> pieces;

    public BoardDto(List<List<PieceDto>> pieces) {
        this.pieces = pieces;
    }

    public List<List<PieceDto>> getPieces() {
        return pieces;
    }
}
