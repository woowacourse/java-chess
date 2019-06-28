package model.board;

import model.Position;
import model.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardBuilder {
    private Map<Position, Piece> pieces = new HashMap<>();

    public BoardBuilder piece(Piece piece) {
        pieces.put(piece.getPosition(), piece);
        return this;
    }

    public BoardBuilder pieces(List<Piece> pieces) {
        for (Piece piece : pieces) {
            this.pieces.put(piece.getPosition(), piece);
        }
        return this;
    }

    public Board build() {
        Board board = new Board(pieces);
        for (Piece piece : pieces.values()) {
            piece.addObserver(board);
        }
        return board;
    }
}
