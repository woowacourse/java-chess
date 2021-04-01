package chess.controller.web.dto;

import java.util.Map;

public class StartResponseDto implements WebResponseDto {
    private final boolean isStart;
    private final Map<String, PieceDto> piecesAndPositions;

    public StartResponseDto(boolean isStart, Map<String, PieceDto> piecesAndPositions) {
        this.isStart = isStart;
        this.piecesAndPositions = piecesAndPositions;
    }

    public Map<String, PieceDto> getPiecesAndPositions() {
        return piecesAndPositions;
    }

    public boolean isStart() {
        return isStart;
    }
}