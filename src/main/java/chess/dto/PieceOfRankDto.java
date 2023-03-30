package chess.dto;

import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class PieceOfRankDto {
    private final List<PieceDto> pieceDtos;

    private PieceOfRankDto(final List<PieceDto> pieceDtos) {
        this.pieceDtos = pieceDtos;
    }

    public static PieceOfRankDto from(final List<Piece> pieces) {
        List<PieceDto> piecesDtos = pieces.stream()
                .map(PieceDto::from)
                .collect(Collectors.toList());
        return new PieceOfRankDto(piecesDtos);
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }
}
