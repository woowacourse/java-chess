package chess.domain.game;

import chess.domain.piece.*;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private static Map<Position, Piece> pieceByPosition = new HashMap<>();

    static {
        setUpGeneral();
        setUpOthers();
    }

    public static Board create() {
        return new Board(pieceByPosition);
    }

    private static void setUpGeneral() {
        setUpGeneralByColor(Color.BLACK, Row.EIGHT);
        setUpGeneralByColor(Color.WHITE, Row.ONE);
    }

    private static void setUpGeneralByColor(Color color, Row row) {
        pieceByPosition.put(Position.of(Column.A, row), new Rook(color));
        pieceByPosition.put(Position.of(Column.B, row), new Knight(color));
        pieceByPosition.put(Position.of(Column.C, row), new Bishop(color));
        pieceByPosition.put(Position.of(Column.D, row), new Queen(color));
        pieceByPosition.put(Position.of(Column.E, row), new King(color));
        pieceByPosition.put(Position.of(Column.F, row), new Bishop(color));
        pieceByPosition.put(Position.of(Column.G, row), new Knight(color));
        pieceByPosition.put(Position.of(Column.H, row), new Rook(color));
    }

    public static void setUpOthers() {
        Pawn blackPawn = new Pawn(Color.BLACK);
        Pawn whitePawn = new Pawn(Color.WHITE);
        Empty empty = new Empty();

        setUpRow(Row.SEVEN, blackPawn);
        setUpRow(Row.SIX, empty);
        setUpRow(Row.FIVE, empty);
        setUpRow(Row.FOUR, empty);
        setUpRow(Row.THREE, empty);
        setUpRow(Row.TWO, whitePawn);
    }

    private static void setUpRow(Row row, Piece piece) {
        for (Column column : Column.values()) {
            pieceByPosition.put(Position.of(column, row), piece);
        }
    }
}
