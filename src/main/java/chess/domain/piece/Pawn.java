package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.PawnStrategy;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(PieceColor pieceColor) {
        super(PieceType.PAWN, pieceColor, new PawnStrategy());
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
