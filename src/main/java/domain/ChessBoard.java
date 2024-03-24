package domain;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.point.Point;
import factory.ChessBoardGenerator;

import java.util.List;

public class ChessBoard {
    private final Pieces pieces;

    public ChessBoard(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPoint(final Point point) {
        final var piece = this.pieces.findPieceWithPoint(point);
        return piece.orElseThrow(() -> new IllegalArgumentException("해당 포인트에는 기물이 없습니다"));
    }

    public void move(final Point startPoint, final Point endPoint) {
        if (startPoint.equals(endPoint)) {
            throw new IllegalArgumentException("같은 위치로 이동할 수 없습니다.");
        }
        if (this.pieces.findPieceWithPoint(startPoint)
                       .isEmpty()) {
            throw new IllegalArgumentException(String.format("%s에는 기물이 없습니다", startPoint));
        }
        this.pieces.findPieceWithPoint(startPoint)
                   .ifPresent(piece -> pieces.move(piece, endPoint));
    }

    public static ChessBoard createDefaultBoard() {
        return ChessBoardGenerator.createDefaultBoard();
    }

    public List<Piece> getPieces() {
        return this.pieces.allPieces();
    }
}
