package chess.web.dto;

import chess.domain.board.Piece;

public class PieceDto {

    private final static String NAME_FORMAT = "%s_%s";
    private final String name;

    private PieceDto(String name) {
        this.name = name;
    }

    public static PieceDto from(Piece piece) {
        return new PieceDto(String.format(NAME_FORMAT, piece.getColor().name().toLowerCase(), piece.getType()));
    }

    public String getName() {
        return name;
    }
}
