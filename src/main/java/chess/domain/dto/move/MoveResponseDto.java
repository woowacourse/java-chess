package chess.domain.dto.move;

import chess.domain.dto.PieceDto;
import chess.domain.dto.ScoreDto;
import java.util.List;

public class MoveResponseDto {

    private final List<PieceDto> pieces;
    private final String currentTeam;
    private final ScoreDto scoreDto;

    public MoveResponseDto(List<PieceDto> pieceDtos, String currentTeam,
        ScoreDto scoreDto) {
        this.pieces = pieceDtos;
        this.currentTeam = currentTeam;
        this.scoreDto = scoreDto;
    }

    public List<PieceDto> getPieces() {
        return pieces;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public ScoreDto getScoreDto() {
        return scoreDto;
    }
}
