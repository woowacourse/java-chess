package chess.dto;

import chess.domain.piece.Piece;

import java.util.List;
import java.util.stream.Collectors;

public class PiecesDto {
    private final List<PieceDto> pieces;

    public PiecesDto(List<Piece> pieces) {
        this.pieces = pieces.stream()
                .map(piece -> new PieceDto(piece.color(), piece.name(), piece.position().key()))
                .collect(Collectors.toList());
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
