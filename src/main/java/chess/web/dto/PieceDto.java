package chess.web.dto;

import chess.domain.piece.Piece;
import chess.web.utils.Converter;

public class PieceDto {
    private final String position;
    private final String imageName;

    private PieceDto(String position, String imageName) {
        this.position = position;
        this.imageName = imageName;
    }

    public static PieceDto of(String position, Piece piece) {
        return new PieceDto(position, Converter.imagePathFrom(piece));
    }

    public static PieceDto of(String position, String imageName) {
        return new PieceDto(position, imageName);
    }

    public String getPosition() {
        return position;
    }

    public String getImageName() {
        return imageName;
    }
}
