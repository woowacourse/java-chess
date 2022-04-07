package chess.service.dto;

import java.util.Map;

public class BoardDto {
    private int id;
    private final Map<String, PieceDto> pieces;

    public BoardDto(Map<String, PieceDto> pieces) {
        this.pieces = pieces;
    }

    public BoardDto(int id, Map<String, PieceDto> pieces) {
        this.id = id;
        this.pieces = pieces;
    }

    public int getId() {
        return id;
    }

    public Map<String, PieceDto> getPieces() {
        return pieces;
    }
}
