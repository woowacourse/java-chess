package chess.domain.piece;

import chess.domain.piece.rule.QueenMoveRule;
import chess.domain.position.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, QueenMoveRule.instance());
    }

    public static Queen of(Color color) {
        return new Queen(color);
    }

    @Override
    public boolean isCatchable(Position from, Position to) {
        return isMovable(from, to);
    }
}
