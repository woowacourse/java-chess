package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceKind;

import java.util.EnumMap;
import java.util.Map;

public class Point {

    private Board board;

    public Point(Board board) {
        this.board = board;
    }

    public double whitePoint() {
        return computePoint(PieceColor.WHITE);
    }

    public double blackPoint() {
        return computePoint(PieceColor.BLACK);
    }

    private double computePoint(final PieceColor pieceColor) {
        double pointSum = 0.0;
        for (XPosition xPosition : XPosition.values()) {
            Map<PieceKind, Integer> existingPiece = countPieceKindAtColumn(pieceColor, xPosition);
            pointSum += columnPoint(existingPiece);
        }
        return pointSum;
    }

    private Map<PieceKind, Integer> countPieceKindAtColumn(final PieceColor pieceColor,
                                                           final XPosition xPosition) {
        Map<PieceKind, Integer> existingPiece = new EnumMap<>(PieceKind.class);
        for (YPosition yPosition : YPosition.values()) {
            Position currentPosition = Position.of(xPosition, yPosition);
            Piece piece = board.pieceAtPosition(currentPosition);
            checkSameColor(pieceColor, existingPiece, piece);
        }

        return existingPiece;
    }

    private void checkSameColor(final PieceColor pieceColor,
        final Map<PieceKind, Integer> existingPiece, final Piece piece) {
        if (piece.isSameColor(pieceColor)) {
            existingPiece.put(piece.kind(), existingPiece.getOrDefault(piece.kind(), 0) + 1);
        }
    }

    private double columnPoint(final Map<PieceKind, Integer> column) {
        double points = 0.0;
        for (Map.Entry<PieceKind, Integer> entry : column.entrySet()) {
            points += eachPiecePoint(entry);
        }
        return points;
    }

    private double eachPiecePoint(final Map.Entry<PieceKind, Integer> entry) {
        double point = entry.getValue() * entry.getKey().point();
        if (entry.getKey() == PieceKind.PAWN && entry.getValue() > 1) {
            point /= 2;
        }
        return point;
    }
}
