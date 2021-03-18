package domain.piece;

import domain.position.Column;
import domain.position.Position;
import domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class Pieces {
    private final List<Piece> pieces = new ArrayList<>();

    public Pieces() {
        setUpGeneral();
        setUpPawn();
    }


    private void setUpGeneral() {
        setUpGeneralByColor(Color.BLACK, Row.EIGHT);
        setUpGeneralByColor(Color.WHITE, Row.ONE);
    }

    private void setUpGeneralByColor(Color color, Row row) {
        pieces.add(new Rook(color, Position.of(Column.A, row)));
        pieces.add(new Knight(color, Position.of(Column.B, row)));
        pieces.add(new Bishop(color, Position.of(Column.C, row)));
        pieces.add(new Queen(color, Position.of(Column.D, row)));
        pieces.add(new King(color, Position.of(Column.E, row)));
        pieces.add(new Bishop(color, Position.of(Column.F, row)));
        pieces.add(new Knight(color, Position.of(Column.G, row)));
        pieces.add(new Rook(color, Position.of(Column.H, row)));
    }

    public void setUpPawn() {
        setUpRow(Row.SEVEN, Color.BLACK);
        setUpRow(Row.TWO, Color.WHITE);
    }

    private void setUpRow(Row row, Color color) {
        for (Column column : Column.values()) {
            pieces.add(new Pawn(color, Position.of(column, row)));
        }
    }

    public Piece getPieceOf(Row row, Column column) {
        return pieces.stream()
                     .filter(piece -> piece.hasPosition(Position.of(column, row)))
                     .findFirst()
                     .orElse(new Empty());
    }
}
