package chess.controller.mapper;

import chess.controller.dto.PieceDto;
import chess.domain.piece.Piece;

public final class PieceDtoMapper {

    public static PieceDto createPieceDto(final Piece piece) {
        return new PieceDto(piece.getPieceType().name(), piece.getCampType().name());
    }
}
