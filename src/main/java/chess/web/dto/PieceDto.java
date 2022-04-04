package chess.web.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private final String position;
    private final String imageName;

    private PieceDto(String position, String imageName) {
        this.position = position;
        this.imageName = imageName;
    }

    public static PieceDto of(String position, Piece piece) {
        String name = piece.getName().toLowerCase();
        String color = piece.getColor().toLowerCase();
        return new PieceDto(position, "images/" + name + "_" + color + ".png");
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
