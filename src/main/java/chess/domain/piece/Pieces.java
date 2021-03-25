package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Pieces {
    private final List<Piece> pieces = new ArrayList<>();

    public Pieces(final Piece... pieces) {
        this.pieces.addAll(Arrays.asList(pieces));
    }

    public Pieces() {
    }

    public void init() {
        setUpGeneral();
        setUpPawn();
    }

    private void setUpGeneral() {
        setUpGeneralByColor(Color.BLACK, Row.EIGHT);
        setUpGeneralByColor(Color.WHITE, Row.ONE);
    }

    private void setUpGeneralByColor(final Color color, final Row row) {
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

    private void setUpRow(final Row row, final Color color) {
        for (final Column column : Column.values()) {
            pieces.add(new Pawn(color, Position.of(column, row)));
        }
    }

    public Piece getPieceOf(final Position position) {
        return pieces.stream()
                     .filter(piece -> piece.hasPosition(position))
                     .findFirst()
                     .orElse(new Empty());
    }

    public boolean hasPieceOf(final Position position) {
        return pieces.stream()
                     .anyMatch(piece -> piece.hasPosition(position));
    }

    public List<Piece> toList() {
        return pieces;
    }

    public void delete(final Piece piece) {
        pieces.remove(piece);
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
