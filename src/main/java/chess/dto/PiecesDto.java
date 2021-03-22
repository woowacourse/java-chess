package chess.dto;

import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class PiecesDto {

    private final List<PieceDto> pieces;

    private PiecesDto(List<PieceDto> pieces) {
        this.pieces = pieces;
    }

    public static PiecesDto from(List<Piece> pieces) {
        final List<PieceDto> castedPieces = pieces.stream()
            .map(PieceDto::from)
            .collect(Collectors.toList());

        return new PiecesDto(castedPieces);
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
