package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.postion.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Position, Piece> cells;

    public Board(final Map<Position, Piece> cells) {
       this.cells = cells;
    }

    public Board movePiece(Position source, Position target) {
        Piece piece = cells.get(source);

        piece.canMove(source, target);

        cells.remove(source);
        cells.put(target, piece);

        final var newCells = new HashMap<>(cells);

        return new Board(newCells);
    }

    public Map<Position, Piece> cells() {
        return cells;
    }
}
