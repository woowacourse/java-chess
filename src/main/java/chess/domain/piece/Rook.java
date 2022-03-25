package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends Piece {
    public Rook(Color color) {
        super(new Name("R"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        RookMoveStrategy moveStrategy = new RookMoveStrategy();
        moveStrategy.isValidateCanMove(color, from, to);
    }
}
