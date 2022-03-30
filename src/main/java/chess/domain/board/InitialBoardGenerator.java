package chess.domain.board;

import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

import chess.domain.piece.Bishop;
import chess.domain.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class InitialBoardGenerator implements BoardGenerator {

    @Override
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
        pointPieces.put(Point.of(1, lineNumber), new Rook(color));
        pointPieces.put(Point.of(2, lineNumber), new Knight(color));
        pointPieces.put(Point.of(3, lineNumber), new Bishop(color));
        pointPieces.put(Point.of(4, lineNumber), new Queen(color));
        pointPieces.put(Point.of(5, lineNumber), new King(color));
        pointPieces.put(Point.of(6, lineNumber), new Bishop(color));
        pointPieces.put(Point.of(7, lineNumber), new Knight(color));
        pointPieces.put(Point.of(8, lineNumber), new Rook(color));
        return pointPieces;
    }

    private Map<Point, Piece> generatePawnLine(int lineNumber, Color color) {
        return IntStream.rangeClosed(LineNumber.MIN, LineNumber.MAX)
            .mapToObj(i -> Point.of(i, lineNumber))
            .collect(toMap(Function.identity(), it -> new Pawn(color)));
    }

    private Map<Point, Piece> generateEmptyLine(int lineNumber) {
        return IntStream.rangeClosed(LineNumber.MIN, LineNumber.MAX)
            .mapToObj(i -> Point.of(i, lineNumber))
            .collect(toMap(Function.identity(), it -> new Empty()));
    }
}
