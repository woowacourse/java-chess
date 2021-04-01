package chess.service.dto;

import chess.domain.piece.Pieces;

import java.util.List;
import java.util.stream.Collectors;

public class PiecesStatusDto {
    private final List<PieceStatusDto> pieces;
    private final ScoreDto scoreDto;

    public PiecesStatusDto(final Pieces pieces, ScoreDto scoreDto) {
        this.pieces = pieces.getPieces()
                .stream()
                .map(PieceStatusDto::new)
                .collect(Collectors.toList());
        this.scoreDto = scoreDto;
    }

    public List<PieceStatusDto> getPieces() {
        return pieces;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }
}
