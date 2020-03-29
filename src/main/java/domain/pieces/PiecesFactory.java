package domain.pieces;

import static domain.point.Column.*;
import static domain.point.Row.*;
import static domain.team.Team.*;

import domain.point.Column;
import domain.point.Point;
import domain.point.Row;
import domain.team.Team;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PiecesFactory {

    private static Map<Point, Piece> pieces = new LinkedHashMap<>();

    static {
        createTeamPieces(EIGHT, BLACK);
        createPawn(SEVEN, BLACK);
        createEmpty();
        createPawn(TWO, WHITE);
        createTeamPieces(ONE, WHITE);
    }

    private static void createTeamPieces(Row row, Team team) {
        pieces.put(Point.of(row, A), new Rook(team));
        pieces.put(Point.of(row, B), new Knight(team));
        pieces.put(Point.of(row, C), new Bishop(team));
        pieces.put(Point.of(row, D), new Queen(team));
        pieces.put(Point.of(row, E), new King(team));
        pieces.put(Point.of(row, F), new Bishop(team));
        pieces.put(Point.of(row, G), new Knight(team));
        pieces.put(Point.of(row, H), new Rook(team));
    }

    private static void createPawn(Row row, Team team) {
        for (Column column : Column.values()) {
            pieces.put(Point.of(row, column), new Pawn(team));
        }
    }

    private static void createEmpty() {
        List<Row> emptyRows = Arrays.asList(SIX, FIVE, FOUR, THREE);
        for (Row row : emptyRows) {
            putEmpty(row);
        }
    }

    private static void putEmpty(Row row) {
        for (Column column : Column.values()) {
            pieces.put(Point.of(row, column), new Empty(NONE));
        }
    }

    public static Map<Point, Piece> create() {
        return pieces;
    }
}