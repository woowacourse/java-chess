package chess.domain.piece.king;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class King extends Piece {
    public King(Position position) {
        super(position, "k", new KingMoveStrategy());
    }
}