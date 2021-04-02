package chess.controller.web.dto;

import chess.domain.piece.attribute.Color;

import java.util.Map;

public class ChessGameResponseDto implements WebResponseDto {
    private final long id;
    private final String color;
    private final Map<String, PieceDto> piecesAndPositions;

    public ChessGameResponseDto(long id, Color color, Map<String, PieceDto> piecesAndPositions) {
        this.id = id;
        this.color = color.name();
        this.piecesAndPositions = piecesAndPositions;
    }

    public long getId() {
        return id;
    }

    public String getColor() {
        return color;
    }

    public Map<String, PieceDto> getPiecesAndPositions() {
        return piecesAndPositions;
    }
}