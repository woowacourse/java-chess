package chess.dto;

import chess.domain.Point;
import chess.domain.pieces.Piece;

import java.util.Objects;

public class PieceDto {
    private final Point point;
    private final Piece piece;

    public PieceDto(Point point, Piece piece) {
        this.point = point;
        this.piece = piece;
    }

    public Point getPoint() {
        return point;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceDto pieceDto = (PieceDto) o;
        return Objects.equals(point, pieceDto.point) &&
                Objects.equals(piece, pieceDto.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, piece);
    }
}
