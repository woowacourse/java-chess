package chess.domain.dto.move;

import chess.domain.dto.PieceDto;
import java.util.List;

public class MoveResponseDto {

    private final List<PieceDto> pieces;

    public MoveResponseDto(List<PieceDto> pieces) {
        this.pieces = pieces;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
