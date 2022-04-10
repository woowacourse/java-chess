package chess.dto;

import chess.domain.piece.Pieces;

public class PiecesDto {

    private final Pieces pieces;

    public PiecesDto(Pieces pieces) {
        this.pieces = pieces;
    }
    
    public Pieces getPieces() {
        return pieces;
    }
}
