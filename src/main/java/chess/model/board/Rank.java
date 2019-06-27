package chess.model.board;

import chess.model.Side;
import chess.model.unit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Rank {
    private final Map<Column, Piece> oneLine;

    Rank() {
        oneLine = new HashMap<>();
    }

    static Rank getFirstLine(final Side side) {
        final Rank rank = new Rank();
        rank.setPiece(Column.A, new Rook(side));
        rank.setPiece(Column.B, new Knight(side));
        rank.setPiece(Column.C, new Bishop(side));
        rank.setPiece(Column.D, new Queen(side));
        rank.setPiece(Column.E, new King(side));
        rank.setPiece(Column.F, new Bishop(side));
        rank.setPiece(Column.G, new Knight(side));
        rank.setPiece(Column.H, new Rook(side));
        return rank;
    }

    static Rank getSecondLine(final Side side) {
        final Rank rank = new Rank();
        for (Column column : Column.values()) {
            rank.setPiece(column, new Pawn(side));
        }
        return rank;
    }

    Piece getPiece(final Column column) {
        return oneLine.get(column);
    }

    void setPiece(final Column column, final Piece piece) {
        oneLine.put(column, piece);
    }

    void removePiece(final Column column) {
        oneLine.remove(column);
    }

    List<Position> getRankPositions(final Row row) {
        List<Position> positions = new ArrayList<>();
        for (Map.Entry<Column, Piece> pieceEntry : oneLine.entrySet()) {
            final Square square = new Square(pieceEntry.getKey(), row);
            positions.add(new Position(square, pieceEntry.getValue()));
        }
        return positions;
    }
}
