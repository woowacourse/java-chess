package chess.domain.game;

import chess.domain.piece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static Map<Position, Piece> pieceByPosition = new HashMap<>();
    private static final Pieces pieces = new Pieces();

    static {
        setUpGeneral();
        setUpPawn();


        setUpGeneral2();
        setUpOthers();
    }

    public static Board create() {
        return new Board(pieces);
    }

    public static Board create2() {
        return new Board(pieceByPosition);
    }

    private static void setUpGeneral() {
        setUpGeneralByColor(Color.BLACK, Row.EIGHT);
        setUpGeneralByColor(Color.WHITE, Row.ONE);
    }

    private static void setUpGeneral2() {
        setUpGeneralByColor2(Color.BLACK, Row.EIGHT);
        setUpGeneralByColor2(Color.WHITE, Row.ONE);
    }
    private static void setUpGeneralByColor(Color color, Row row) {
        pieces.add(new Rook(color, Position.of(Column.A, row)));
        pieces.add(new Knight(color, Position.of(Column.B, row)));
        pieces.add(new Bishop(color, Position.of(Column.C, row)));
        pieces.add(new Queen(color, Position.of(Column.D, row)));
        pieces.add(new King(color, Position.of(Column.E, row)));
        pieces.add(new Bishop(color, Position.of(Column.F, row)));
        pieces.add(new Knight(color, Position.of(Column.G, row)));
        pieces.add(new Rook(color, Position.of(Column.H, row)));
    }
    private static void setUpGeneralByColor2(Color color, Row row) {
        pieceByPosition.put(Position.of(Column.A, row), new Rook(color, Position.of(Column.A, row)));
        pieceByPosition.put(Position.of(Column.B, row), new Knight(color, Position.of(Column.B, row)));
        pieceByPosition.put(Position.of(Column.C, row), new Bishop(color, Position.of(Column.C, row)));
        pieceByPosition.put(Position.of(Column.D, row), new Queen(color, Position.of(Column.D, row)));
        pieceByPosition.put(Position.of(Column.E, row), new King(color, Position.of(Column.E, row)));
        pieceByPosition.put(Position.of(Column.F, row), new Bishop(color, Position.of(Column.F, row)));
        pieceByPosition.put(Position.of(Column.G, row), new Knight(color, Position.of(Column.G, row)));
        pieceByPosition.put(Position.of(Column.H, row), new Rook(color, Position.of(Column.H, row)));
    }

    public static void setUpPawn() {
        setUpRow(Row.SEVEN, Color.BLACK);
        setUpRow(Row.TWO, Color.WHITE);
    }

    private static void setUpRow(Row row, Color color) {
        for (Column column : Column.values()) {
            pieces.add(new Pawn(color, Position.of(column, row)));
        }
    }

    public static void setUpOthers() {
        Pawn blackPawn = new Pawn(Color.BLACK, Position.from("a1"));
        Pawn whitePawn = new Pawn(Color.WHITE, Position.from("a1"));
        Empty empty = new Empty();

        setUpRow2(Row.SEVEN, blackPawn);
        setUpRow2(Row.SIX, empty);
        setUpRow2(Row.FIVE, empty);
        setUpRow2(Row.FOUR, empty);
        setUpRow2(Row.THREE, empty);
        setUpRow2(Row.TWO, whitePawn);
    }

    private static void setUpRow2(Row row, Piece piece) {
        for (Column column : Column.values()) {
            pieceByPosition.put(Position.of(column, row), piece);
        }
    }
}
