package chess.dto;

import java.util.List;

public class PiecesDto {

    private final List<PieceDto> pieces;

    public PiecesDto(List<PieceDto> pieces) {
        this.pieces = pieces;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

}
