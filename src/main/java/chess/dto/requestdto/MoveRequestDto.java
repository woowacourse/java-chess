package chess.dto.requestdto;

import chess.dto.GridDto;
import chess.dto.PieceDto;

import java.util.List;

public class MoveRequestDto {
    private final List<PieceDto> piecesDto;
    private final String sourcePosition;
    private final String targetPosition;
    private final GridDto gridDto;

    public MoveRequestDto(List<PieceDto> piecesDto, String sourcePosition, String targetPosition, GridDto gridDto) {
        this.piecesDto = piecesDto;
        this.sourcePosition = sourcePosition;
        this.targetPosition = targetPosition;
        this.gridDto = gridDto;
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

    public GridDto getGridDto() {
        return gridDto;
    }

    @Override
    public String toString() {
        return "MoveRequestDto{" +
                "piecesDto=" + piecesDto +
                ", sourcePosition='" + sourcePosition + '\'' +
                ", targetPosition='" + targetPosition + '\'' +
                ", gridDto=" + gridDto +
                '}';
    }
}
