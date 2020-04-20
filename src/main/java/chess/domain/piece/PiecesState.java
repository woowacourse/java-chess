package chess.domain.piece;

import chess.domain.piece.position.Position;

import java.util.Map;

public class PiecesState {
    private final Map<Position, Piece> pieces;


    public PiecesState(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece getPiece(Position position) {
        return pieces.get(position);
    }
}
