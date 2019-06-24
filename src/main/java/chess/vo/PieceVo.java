package chess.vo;

import chess.domain.Point;
import chess.domain.pieces.Piece;

import java.util.Objects;

public class PieceVo {
    private final Point point;
    private final Piece piece;

    public PieceVo(Point point, Piece piece) {
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
        PieceVo pieceVo = (PieceVo) o;
        return Objects.equals(point, pieceVo.point) &&
                Objects.equals(piece, pieceVo.piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, piece);
    }
}
