package dto;

import java.util.Objects;

import domain.board.Square;
import domain.piece.Piece;

public class MoveHistoryDto {
    private final String source;
    private final String target;
    private final String piece;

    public MoveHistoryDto(String source, String target, String piece) {
        this.source = source;
        this.target = target;
        this.piece = piece;
    }

    public static MoveHistoryDto of(Square source, Square target, Piece piece) {
        return new MoveHistoryDto(source.parseToString(), target.parseToString(), piece.getType().name());
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoveHistoryDto that = (MoveHistoryDto) o;
        return Objects.equals(source, that.source) && Objects.equals(target, that.target)
                && Objects.equals(piece, that.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, target, piece);
    }
}
