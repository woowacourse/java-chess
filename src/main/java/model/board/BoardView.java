package model.board;

import model.Position;
import model.piece.Piece;
import model.piece.PieceColor;

import java.util.HashMap;
import java.util.Map;

public class BoardView {
    private Map<Position, PieceColor> pieces;

    public PieceColor getPieceColorAt(Position position) {
        return pieces.getOrDefault(position, PieceColor.EMPTY);
    }

    public BoardView(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>();
        for (Piece piece : pieces.values()) {
            this.pieces.put(piece.getPosition(), piece.getPieceColor());
        }
    }
}
