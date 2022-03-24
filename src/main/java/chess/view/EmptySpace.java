package chess.view;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.Piece;

public class EmptySpace extends Piece {

    public EmptySpace() {
        super("blank", Color.BLACK);
    }

    @Override
    public Direction findValidDirection(Position current, Position target) {
        throw new UnsupportedOperationException();
    }
}
