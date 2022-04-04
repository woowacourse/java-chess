package chess.web.service;

import java.util.Map;

public class BoardDto {
    private final Map<String, PieceDto> pieces;

    public BoardDto(Map<String, PieceDto> pieces) {
        this.pieces = pieces;
    }

    public Map<String, PieceDto> getPieces() {
        return pieces;
    }
}
