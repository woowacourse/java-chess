package chess.service.dto;

import chess.domain.piece.Pieces;

import java.util.List;
import java.util.stream.Collectors;

public class PiecesStatusDto {
    private final List<PieceStatusDto> pieces;
    private final ScoreDto scoreDto;
    private final boolean isGameOver;

    public PiecesStatusDto(final Pieces pieces, final ScoreDto scoreDto, final boolean isGameOver) {
        this.pieces = pieces.getPieces()
                .stream()
                .map(PieceStatusDto::new)
                .collect(Collectors.toList());
        this.scoreDto = scoreDto;
        this.isGameOver = isGameOver;
    }

    public List<PieceStatusDto> getPieces() {
        return pieces;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
