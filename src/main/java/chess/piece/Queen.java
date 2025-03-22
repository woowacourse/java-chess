package chess.piece;

import chess.Color;
import chess.Position;
import chess.board.Board;
import java.util.List;

public class Queen extends Piece {

    public Queen(final Color color) {
        super(color);
    }

    @Override
    protected List<Position> findRoot(Position start, Position goal) {
        return List.of();
    }

    @Override
    protected void validateMiddlePath(Board board, List<Position> root) {

    }
}
