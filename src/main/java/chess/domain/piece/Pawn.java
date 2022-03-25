package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(new Name("P"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        PawnMoveStrategy moveStrategy = new PawnMoveStrategy();
        moveStrategy.isValidateCanMove(color, from, to);
    }
}
