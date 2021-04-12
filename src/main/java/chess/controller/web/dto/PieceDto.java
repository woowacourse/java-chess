package chess.controller.web.dto;

import java.util.Objects;

public class PieceDto {
    private final String notation;
    private final String color;

    public PieceDto(String notation, String color) {
        this.notation = notation;
        this.color = color;
    }

    public String getNotation() {
        return notation;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return notation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceDto)) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(getNotation(), pieceDto.getNotation()) && Objects.equals(getColor(), pieceDto.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNotation(), getColor());
    }
}
