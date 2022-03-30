package chess.domain.piece;

import chess.domain.piece.notation.Color;
import chess.domain.piece.notation.ColorNotation;
import chess.domain.piece.notation.PieceNotation;
import chess.domain.position.Direction;

public final class Knight extends Piece {

    public Knight(final Color color) {
        super(new ColorNotation(color, PieceNotation.KNIGHT), Direction.knightDirection());
    }
}
