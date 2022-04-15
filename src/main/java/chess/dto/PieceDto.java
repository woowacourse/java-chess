package chess.dto;

import chess.domain.board.Piece;
import java.util.Objects;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PieceDto pieceDto = (PieceDto) o;
        return name.equals(pieceDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
