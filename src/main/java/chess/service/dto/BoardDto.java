package chess.service.dto;

import java.util.List;

public class BoardDto {
    private final List<PieceWithSquareDto> pieces;

    public BoardDto(List<PieceWithSquareDto> pieces) {
        this.pieces = pieces;
    }

    public List<PieceWithSquareDto> getPieces() {
        return pieces;
    }
}
