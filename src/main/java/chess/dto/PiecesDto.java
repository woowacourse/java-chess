package chess.dto;

import chess.domain.result.BoardResult;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PiecesDto {

    private final List<PieceDto> pieces;

    private PiecesDto(final List<PieceDto> pieces) {
        this.pieces = pieces;
    }

    public static PiecesDto createEmpty() {
        return new PiecesDto(Collections.emptyList());
    }

    public static PiecesDto from(final BoardResult boardResult) {
        List<PieceDto> castedPieces = boardResult.getPieces()
            .stream()
            .map(PieceDto::from)
            .collect(Collectors.toList());

        return new PiecesDto(castedPieces);
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }
}
