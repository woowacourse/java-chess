package chess.domain.piece;

import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.QueenStrategy;
import java.util.List;

public class Queen extends Piece {

    public Queen(PieceColor pieceColor) {
        super(PieceType.QUEEN, pieceColor, new QueenStrategy());
    }

    @Override
    public List<Direction> directions() {
        return null;
    }
}
