package chess.domain.piece;

import chess.domain.Color;
import chess.domain.PieceType;

public class Empty extends Piece {
    public Empty() {
        super(Color.NONE, PieceType.NONE);
    }
}
