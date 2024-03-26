package domain;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.point.Point;
import dto.RouteDto;
import factory.ChessBoardGenerator;

import java.util.Map;

public class ChessBoard {
    private final Pieces pieces;

    public ChessBoard(final Pieces pieces) {
        this.pieces = pieces;
    }

    public Piece findPieceByPoint(final Point point) {
        return this.pieces.findPieceWithPoint(point)
                .orElseThrow(() -> new IllegalArgumentException("해당 포인트에는 기물이 없습니다"));
    }

    public void move(final RouteDto dto) {
        final var startPoint = dto.getStartPoint();
        final var endPoint = dto.getEndPoint();
        final var piece = findPieceByPoint(startPoint);

        if (pieces.canMove(piece, endPoint)) {
            pieces.move(piece, endPoint);
            return;
        }
        throw new IllegalArgumentException(
                String.format("%s 는 %s 에서 %s로 이동할 수 없습니다.", piece.getStatus(), startPoint, endPoint));
    }

    public static ChessBoard createDefaultBoard() {
        return ChessBoardGenerator.createDefaultBoard();
    }

    public Map<Point, Piece> toMap() {
        return pieces.toMap();
    }
}
