package chess.web.dto;

import chess.domain.piece.Piece;

public class PieceDto {

    private final String name;

    public PieceDto(final String name) {
        this.name = name;
    }

    public static PieceDto from(final Piece piece) {
        final String character = piece.getCharacter();
        if (piece.isNullPiece()) {
            return new PieceDto("null");
        }
        if (piece.isBlack()) {
            return new PieceDto("b_" + character);
        }

        return new PieceDto("w_" + character);
    }

    public String getName() {
        return name;
    }
}
