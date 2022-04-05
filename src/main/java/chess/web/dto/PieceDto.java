package chess.web.dto;

import chess.domain.piece.Piece;
import chess.web.utils.Converter;

public class PieceDto {
    private final String position;
    private final String name;
    private final String imageName;

    private PieceDto(String position, String name, String imageName) {
        this.position = position;
        this.name = name;
        this.imageName = imageName;
    }

    public static PieceDto of(String position, Piece piece) {
        return new PieceDto(position, piece.getName(), Converter.imagePathFrom(piece));
    }

    public static PieceDto of(String position, String name, String imageName) {
        return new PieceDto(position, name, imageName);
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PieceDto pieceDto = (PieceDto)o;

        if (getPosition() != null ? !getPosition().equals(pieceDto.getPosition()) : pieceDto.getPosition() != null)
            return false;
        return getImageName() != null ? getImageName().equals(pieceDto.getImageName()) :
            pieceDto.getImageName() == null;
    }

    @Override
    public int hashCode() {
        int result = getPosition() != null ? getPosition().hashCode() : 0;
        result = 31 * result + (getImageName() != null ? getImageName().hashCode() : 0);
        return result;
    }
}
