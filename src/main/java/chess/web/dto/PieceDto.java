package chess.web.dto;

import chess.domain.board.Piece;

public class PieceDto {

    private final String name;

    private PieceDto(String name) {
        this.name = name;
    }

    public static PieceDto from(Piece piece) {
        return new PieceDto(piece.getName());
    }

    public String getName() {
        return name;
    }
}
