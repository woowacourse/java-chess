package chess.dto;

import chess.domain.piece.Pieces;
import java.util.List;

public class PiecesDto {

    private final Pieces pieces;

    public PiecesDto() {
        this.pieces = new Pieces(List.of());
    }

    public PiecesDto(Pieces pieces) {
        this.pieces = pieces;
    }
    
    public Pieces getPieces() {
        return pieces;
    }
}
