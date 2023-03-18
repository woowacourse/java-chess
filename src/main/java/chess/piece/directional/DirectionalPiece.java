package chess.piece.directional;

import java.util.List;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

public abstract class DirectionalPiece extends Piece {

    protected List<Direction> directions;

    public DirectionalPiece(Position position, Side side) {
        super(position, side);
    }

    protected abstract List<Direction> initDirections();
}
