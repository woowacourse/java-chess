package chess.view;

import chess.domain.piece.Piece;

public class PieceDto {

    private final String img;

    private PieceDto(String img) {
        this.img = img;
    }

    public static PieceDto of(Piece piece) {
        if (piece.isBlack()) {
            return new PieceDto("black-" + piece.getName());
        }

        if (piece.isWhite()) {
            return new PieceDto("white-" + piece.getName());
        }

        return new PieceDto("blank");
    }

    public String getImg() {
        return img;
    }
}
