package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        allMovements.add(List.of(Movement.UP));
        allMovements.add(List.of(Movement.UP_UP));
        allMovements.add(List.of(Movement.LEFT_UP));
        allMovements.add(List.of(Movement.RIGHT_UP));
    }

    public Pawn(final Color color) {
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
