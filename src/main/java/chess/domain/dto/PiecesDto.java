package chess.domain.dto;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class PiecesDto {

    private List<PieceDto> pieces = new ArrayList<>();

    public PiecesDto(List<Piece> pieces) {
        for(Piece piece : pieces) {
            this.pieces.add(new PieceDto(piece));
        }

    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

}
