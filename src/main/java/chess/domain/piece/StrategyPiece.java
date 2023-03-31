package chess.domain.piece;

import chess.domain.position.Move;

public class StrategyPiece extends Piece {

    public StrategyPiece(Color color, PieceType type) {
        super(color, type);
    }

    @Override
    public boolean canMove(Move move, Piece targetPiece) {
        return type.getMoveStrategy().canMove(move);
    }
}
