package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Point, Piece> pointPieces;

    private Board(Map<Point, Piece> pointPieces) {
        this.pointPieces = pointPieces;
    }

    public static Board of(BoardGenerator generator) {
        return new Board(generator.generate());
    }

    public Map<Point, Piece> getPointPieces() {
        return Map.copyOf(pointPieces);
    }

    /**
     * 1. 내 말인지 체크 (보드) v
     * 2. 가는 길에 말이 없는지 체크 (Piece -> 보드)
     * 3. 도착지에 내 말이 없는지 (보드) v
     * 4. 해당 말이 실제로 갈수있는지(규칙에따라서) -> 피스
     */
    public boolean move(List<String> arguments, Color turnColor) {
        validateArgumentSize(arguments);
        Point from = Point.of(arguments.get(0));
        Point to = Point.of(arguments.get(1));

        Piece fromPiece = pointPieces.get(from);
        Piece toPiece = pointPieces.get(to);
        validatePieceColor(turnColor, fromPiece, toPiece);

        boolean possible = fromPiece.move(this, from, to);

        if (possible) {
            pointPieces.put(to, fromPiece);
            pointPieces.put(from, new Empty(Color.NONE));
        }
        return possible;
    }

    private void validateArgumentSize(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("");
        }
    }

    private void validatePieceColor(Color turnColor, Piece fromPiece, Piece toPiece) {
        if (!fromPiece.isSameColor(turnColor) || toPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("");
        }
    }

    public boolean isEmpty(Point point) {
        Piece piece = pointPieces.get(point);
        return piece.isEmpty();
    }
}
