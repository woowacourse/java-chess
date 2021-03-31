package chess.service.dto;

import chess.domain.piece.Pieces;

import java.util.List;
import java.util.stream.Collectors;

public class PiecesStatusDto {
    private final List<PieceStatusDto> pieces;

    public PiecesStatusDto(final Pieces pieces) {
        this.pieces = pieces.getPieces()
                .stream()
                .map(PieceStatusDto::new)
                .collect(Collectors.toList());
    }

    public List<PieceStatusDto> getPieces() {
        return pieces;
    }
}
