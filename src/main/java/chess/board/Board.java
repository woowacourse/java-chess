package chess.board;

import chess.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final List<Piece> pieces;

    public Board(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }
}
