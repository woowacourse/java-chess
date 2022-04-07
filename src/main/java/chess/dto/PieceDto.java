package chess.dto;

import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Objects;

public class PieceDto {

    private final String position;
    private final String name;

    public PieceDto(String position, String name) {
        this.position = position;
        this.name = name;
    }

    public static PieceDto of(Position position, String name) {
        return new PieceDto((char) (position.getFile() + 'a') + String.valueOf(position.getRank()), name);
    }

    public static PieceDto from(Piece piece) {
        return PieceDto.of(piece.getPosition(), Character.toString(piece.getName()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(position, pieceDto.position) && Objects.equals(name, pieceDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, name);
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
