package chess.dto.requestdto;

import chess.dto.PieceDto;

import java.util.List;

public class MoveRequestDto {
    private final List<PieceDto> piecesDto;
    private final String sourcePosition;
    private final String targetPosition;

    public MoveRequestDto(List<PieceDto> piecesDto, String sourcePosition, String targetPosition) {
        this.piecesDto = piecesDto;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
    }

    public List<PieceDto> getPiecesDto() {
        return piecesDto;
    }

    public String getSourcePosition() {
        return sourcePosition;
    }

    public String getTargetPosition() {
        return targetPosition;
    }
}
