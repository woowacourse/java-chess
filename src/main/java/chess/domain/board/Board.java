package chess.domain.board;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class Board {

    private final Map<Point, Piece> pointPieces;

    private Board(Map<Point, Piece> pointPieces) {
        this.pointPieces = new HashMap<>(pointPieces);
    }

    public static Board of(BoardGenerator generator) {
        return new Board(generator.generate());
    }

    public boolean move(List<String> arguments, Color turnColor) {
        validateArgumentSize(arguments);
        Point from = Point.of(arguments.get(0));
        Point to = Point.of(arguments.get(1));

        Piece fromPiece = pointPieces.get(from);
        Piece toPiece = pointPieces.get(to);
        validateAllyMove(turnColor, fromPiece);
        validateNotAllyAttack(turnColor, toPiece);

        if (!fromPiece.move(this, from, to)) {
            throw new IllegalArgumentException("움직일 수 없습니다.");
        }
        movePiece(from, to, fromPiece);
        return toPiece.isSameType(PieceType.KING);
    }

    private void movePiece(Point from, Point to, Piece fromPiece) {
        pointPieces.put(to, fromPiece);
        pointPieces.put(from, new Empty());
    }

    private void validateArgumentSize(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 출발지와 도착자를 입력해주세요.(move a1 a2)");
        }
    }

    private void validateAllyMove(Color turnColor, Piece fromPiece) {
        if (!fromPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("[ERROR] 자신의 말을 움직여야 합니다.");
        }
    }

    private void validateNotAllyAttack(Color turnColor, Piece toPiece) {
        if (toPiece.isSameColor(turnColor)) {
            throw new IllegalArgumentException("[ERROR] 아군을 공격할 수 없습니다.");
        }
    }

    public boolean isEmpty(Point point) {
        Piece piece = pointPieces.get(point);
        return piece.isSameType(PieceType.EMPTY);
    }

    public Map<Color, Double> calculateScore() {
        Map<Color, Double> map = new LinkedHashMap<>();
        map.put(Color.WHITE, PieceType.calculateScore(pointPieces, Color.WHITE));
        map.put(Color.BLACK, PieceType.calculateScore(pointPieces, Color.BLACK));
        return map;
    }

    public Map<Point, Piece> getPointPieces() {
        return Map.copyOf(pointPieces);
    }
}
