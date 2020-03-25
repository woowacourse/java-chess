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
        pieces.put(new Point(EIGHT, A), new Rook(BLACK));
        pieces.put(new Point(EIGHT, C), new Bishop(BLACK));
        pieces.put(new Point(EIGHT, B), new Knight(BLACK));
        pieces.put(new Point(EIGHT, D), new Queen(BLACK));
        pieces.put(new Point(EIGHT, E), new King(BLACK));
        pieces.put(new Point(EIGHT, F), new Bishop(BLACK));
        pieces.put(new Point(EIGHT, G), new Knight(BLACK));
        pieces.put(new Point(EIGHT, H), new Rook(BLACK));

        for (Column column: Column.values()) {
            pieces.put(new Point(SEVEN, column), new Pawn(BLACK));
        }
    }

    private static void createEmpty() {
        List<Row> emptyRows = Arrays.asList(SIX, FIVE, FOUR, THREE);
        for (Row row : emptyRows) {
            for (Column column : Column.values()) {
                pieces.put(new Point(row, column), new Empty(NONE));
            }
        }
    }

    private static void createWhiteTeamPieces() {
        for (Column column: Column.values()) {
            pieces.put(new Point(TWO, column), new Pawn(WHITE));
        }

        pieces.put(new Point(ONE, A), new Rook(WHITE));
        pieces.put(new Point(ONE, B), new Knight(WHITE));
        pieces.put(new Point(ONE, C), new Bishop(WHITE));
        pieces.put(new Point(ONE, D), new Queen(WHITE));
        pieces.put(new Point(ONE, E), new King(WHITE));
        pieces.put(new Point(ONE, F), new Bishop(WHITE));
        pieces.put(new Point(ONE, G), new Knight(WHITE));
        pieces.put(new Point(ONE, H), new Rook(WHITE));
    }

    public static Map<Point, Piece> create() {
        return pieces;
    }
}