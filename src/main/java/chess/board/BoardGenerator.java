package chess.board;

import chess.piece.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class BoardGenerator {

    public Map<Point, Piece> generate() {
        Map<Point, Piece> pointPieces = new HashMap<>();
        pointPieces.putAll(generateSpecialLine(1, Color.WHITE));
        pointPieces.putAll(generatePawnLine(2, Color.WHITE));
        pointPieces.putAll(generateEmptyLine(3));
        pointPieces.putAll(generateEmptyLine(4));
        pointPieces.putAll(generateEmptyLine(5));
        pointPieces.putAll(generateEmptyLine(6));
        pointPieces.putAll(generatePawnLine(7, Color.BLACK));
        pointPieces.putAll(generateSpecialLine(8, Color.BLACK));
        return pointPieces;
    }

    private Map<Point, Piece> generateSpecialLine(int lineNumber, Color color) {
        Map<Point, Piece> pointPieces = new HashMap<>();
        pointPieces.put(Point.of(lineNumber, 1), new Rook(color));
        pointPieces.put(Point.of(lineNumber, 2), new Knight(color));
        pointPieces.put(Point.of(lineNumber, 3), new Bishop(color));
        pointPieces.put(Point.of(lineNumber, 4), new Queen(color));
        pointPieces.put(Point.of(lineNumber, 5), new King(color));
        pointPieces.put(Point.of(lineNumber, 6), new Bishop(color));
        pointPieces.put(Point.of(lineNumber, 7), new Knight(color));
        pointPieces.put(Point.of(lineNumber, 8), new Rook(color));
        return pointPieces;
    }

    private Map<Point, Piece> generatePawnLine(int lineNumber, Color color) {
        return IntStream.rangeClosed(LineNumber.MIN, LineNumber.MAX)
                .mapToObj(i -> Point.of(lineNumber, i))
                .collect(toMap(Function.identity(), it -> new Pawn(color)));
    }

    private Map<Point, Piece> generateEmptyLine(int lineNumber) {
        return IntStream.rangeClosed(LineNumber.MIN, LineNumber.MAX)
                .mapToObj(i -> Point.of(lineNumber, i))
                .collect(toMap(Function.identity(), it -> new Empty(Color.NONE)));
    }
}
