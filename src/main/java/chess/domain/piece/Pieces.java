package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Pieces {
    private static final String WRONG_CHESS_ERROR = "잘못된 체스 이름입니다.";
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

    public Piece findPieceByName(String name) {
        if ("B".equals(name.substring(0,1))) {
            return findPieceByColor(Color.BLACK, name.substring(1, 2));
        } else {
            return findPieceByColor(Color.WHITE, name.substring(1, 2));
        }
    }

    private Piece findPieceByColor (Color color, String symbol) {
        for (Piece piece : pieces) {
            if (piece.isSameColor(color) && piece.getClass() == findPieceBySymbol(symbol)) {
                return piece;
            }
        }
        throw new IllegalArgumentException(WRONG_CHESS_ERROR);
    }

    private Object findPieceBySymbol(String key) {
        Map<String, Object> symbolOfPieces = new HashMap<>();
        symbolOfPieces.put("R", Rook.class);
        symbolOfPieces.put("N", Knight.class);
        symbolOfPieces.put("B", Bishop.class);
        symbolOfPieces.put("Q", Queen.class);
        symbolOfPieces.put("K", King.class);
        symbolOfPieces.put("P", Pawn.class);
        return symbolOfPieces.get(key);
    }
}
