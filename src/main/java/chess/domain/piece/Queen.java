package chess.domain.piece;

import chess.domain.piece.rule.QueenMoveRule;
import chess.domain.position.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, QueenMoveRule.instance());
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
