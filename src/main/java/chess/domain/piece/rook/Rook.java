package chess.domain.piece.rook;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Position position) {
        super(position, "r", new RookMoveStrategy());
    }
}
