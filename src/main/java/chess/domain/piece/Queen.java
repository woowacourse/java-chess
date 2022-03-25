package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Name;
import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends Piece {
    public Queen(Color color) {
        super(new Name("Q"), color);
    }

    @Override
    public void canMove(Board board, Position from, Position to) {
        QueenMoveStrategy moveStrategy = new QueenMoveStrategy();
        moveStrategy.isValidateCanMove(color, from, to);
    }
}
