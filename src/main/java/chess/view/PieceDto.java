package chess.view;

import chess.domain.piece.Piece;

public class PieceDto {

    private final String piece;

    private PieceDto(String piece) {
        this.piece = piece;
    }

    public static PieceDto of(Piece piece) {
        if (piece.isBlack()) {
            return new PieceDto("black-" + piece.getName());
        }

        if (piece.isWhite()) {
            return new PieceDto("white-" + piece.getName());
        }

        return new PieceDto("none-.");
    }

    public String getPiece() {
        return piece;
    }

    public String getTeam() {
        return piece.split("-")[0];
    }

    public String getName() {
        return piece.split("-")[1];
    }

}
