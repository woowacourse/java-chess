package chess.service;

import chess.controller.dto.PieceDto;
import chess.domain.piece.Piece;

public final class PieceService {

    public PieceDto createPieceDto(final Piece piece) {
        return new PieceDto(piece.getPieceType().name(), piece.getCampType().name());
    }
}
