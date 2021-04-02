package chess.controller.web.dto;

import chess.domain.piece.attribute.Color;

import java.util.Map;

public class StartResponseDto implements WebResponseDto {
    private final String color;
    private final Map<String, PieceDto> piecesAndPositions;

    public StartResponseDto(Color color, Map<String, PieceDto> piecesAndPositions) {
        this.color = color.name();
        this.piecesAndPositions = piecesAndPositions;
    }

    public Map<String, PieceDto> getPiecesAndPositions() {
        return piecesAndPositions;
    }

    public String getColor() {
        return color;
    }

}