package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import chess.domain.Color;
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

        validate(turnColor, from, to);
        boolean isKingDead = isKingDead(to);
        movePiece(from, to);
        return isKingDead;
    }

    private boolean isKingDead(Point to) {
        return pointPieces.get(to)
            .isSameType(PieceType.KING);
    }

    private void validateArgumentSize(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new IllegalArgumentException("[ERROR] 출발지와 도착자를 입력해주세요.(move a1 a2)");
        }
    }

    private void validate(Color turnColor, Point from, Point to) {
        validateAllyMove(turnColor, pointPieces.get(from));
        validateNotAllyAttack(turnColor, pointPieces.get(to));
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

    private void movePiece(Point from, Point to) {
        Piece fromPiece = pointPieces.get(from);
        tryMove(from, to, fromPiece);
        replacePiecePoint(from, to, fromPiece);
    }

    private void tryMove(Point from, Point to, Piece fromPiece) {
        if (!fromPiece.move(this, from, to)) {
            throw new IllegalArgumentException("[ERROR] 해당 위치로 움직일 수 없습니다.");
        }
    }

    private void replacePiecePoint(Point from, Point to, Piece fromPiece) {
        pointPieces.put(to, fromPiece);
        pointPieces.put(from, new Empty());
    }

    public boolean isEmpty(Point point) {
        Piece piece = pointPieces.get(point);
        return piece.isSameType(PieceType.EMPTY);
    }

    public Map<Color, Double> calculateScore() {
        return Stream.of(Color.WHITE, Color.BLACK)
            .collect(toMap(
                Function.identity(),
                color -> PieceType.calculateScore(pointPieces, color)
            ));
    }

    public Map<Point, Piece> getPointPieces() {
        return Map.copyOf(pointPieces);
    }
}
