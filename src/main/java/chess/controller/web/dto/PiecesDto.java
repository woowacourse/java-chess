package chess.controller.web.dto;

import java.util.Map;

public class PiecesDto {
    private final Map<PieceDto, String> piecesAndPostions;

    public PiecesDto(Map<PieceDto, String> piecesAndPostions) {
        this.piecesAndPostions = piecesAndPostions;
    }

    public Map<PieceDto, String> getPiecesAndPostions() {
        return piecesAndPostions;
    }
}
