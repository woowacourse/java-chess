package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends Piece {
    public King(Color color) {
        super(new Name("K"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        KingMoveStrategy moveStrategy = new KingMoveStrategy();
        moveStrategy.isValidateCanMove(color, from, to);
    }
}
