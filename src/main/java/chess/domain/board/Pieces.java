package chess.domain.board;

import java.util.Map;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;

public class Pieces {

    private final Map<Coordinate, Piece> pieces;

    public Pieces(Map<Coordinate, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findByCoordinate(Coordinate coordinate) {
        return pieces.getOrDefault(coordinate, EmptyPiece.getInstance());
    }

    public boolean isPiecePresent(Coordinate coordinate) {
        return pieces.containsKey(coordinate);
    }

    void swap(Coordinate source, Coordinate target) {
        Piece sourcePiece = pieces.get(source);
        pieces.remove(source);
        pieces.put(target, sourcePiece);
    }
}
