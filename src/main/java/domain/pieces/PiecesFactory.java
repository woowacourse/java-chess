package domain.pieces;

import static domain.point.Column.*;
import static domain.point.Row.*;
import static domain.team.Team.*;

import domain.point.Column;
import domain.point.Point;
import domain.point.Row;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PiecesFactory {
    private static Map<Point, Piece> pieces = new LinkedHashMap<>();

    static {
        createBlackTeamPieces();
        createEmpty();
        createWhiteTeamPieces();
    }

    private static void createBlackTeamPieces() {
        pieces.put(Point.of(EIGHT, A), new Rook(BLACK));
        pieces.put(Point.of(EIGHT, B), new Knight(BLACK));
        pieces.put(Point.of(EIGHT, C), new Bishop(BLACK));
        pieces.put(Point.of(EIGHT, D), new Queen(BLACK));
        pieces.put(Point.of(EIGHT, E), new King(BLACK));
        pieces.put(Point.of(EIGHT, F), new Bishop(BLACK));
        pieces.put(Point.of(EIGHT, G), new Knight(BLACK));
        pieces.put(Point.of(EIGHT, H), new Rook(BLACK));

        for (Column column: Column.values()) {
            pieces.put(Point.of(SEVEN, column), new Pawn(BLACK));
        }
    }

    private static void createEmpty() {
        List<Row> emptyRows = Arrays.asList(SIX, FIVE, FOUR, THREE);
        for (Row row : emptyRows) {
            for (Column column : Column.values()) {
                pieces.put(Point.of(row, column), new Empty(NONE));
            }
        }
    }

    private static void createWhiteTeamPieces() {
        for (Column column: Column.values()) {
            pieces.put(Point.of(TWO, column), new Pawn(WHITE));
        }

        pieces.put(Point.of(ONE, A), new Rook(WHITE));
        pieces.put(Point.of(ONE, B), new Knight(WHITE));
        pieces.put(Point.of(ONE, C), new Bishop(WHITE));
        pieces.put(Point.of(ONE, D), new Queen(WHITE));
        pieces.put(Point.of(ONE, E), new King(WHITE));
        pieces.put(Point.of(ONE, F), new Bishop(WHITE));
        pieces.put(Point.of(ONE, G), new Knight(WHITE));
        pieces.put(Point.of(ONE, H), new Rook(WHITE));
    }

    public static Map<Point, Piece> create() {
        return pieces;
    }
}