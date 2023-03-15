package chess.board;

import chess.piece.Piece;
import chess.piece.Pieces;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private final Pieces pieces;

    public Board(final Pieces pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces.getPieces();
    }
}
