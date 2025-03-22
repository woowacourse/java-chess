package chess.piece;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        allMovements.add(List.of(Movement.UP));
        allMovements.add(List.of(Movement.DOWN));
        allMovements.add(List.of(Movement.LEFT));
        allMovements.add(List.of(Movement.RIGHT));
        allMovements.add(List.of(Movement.RIGHT_UP));
        allMovements.add(List.of(Movement.LEFT_UP));
        allMovements.add(List.of(Movement.RIGHT_DOWN));
        allMovements.add(List.of(Movement.LEFT_DOWN));
    }

    public King(final Color color) {
        super(color);
    }

    protected List<Position> findRoot(Position start, Position goal) {
        for (List<Movement> movements : allMovements) {
            List<Position> root = new ArrayList<>();
            Position now = start;
            root.add(now);
            for (Movement movement : movements) {
                if (now.canNotMove(movement)) {
                    break;
                }
                now = now.move(movement);
                root.add(now);
            }
            if (now.equals(goal)) {
                return root;
            }
        }
        throw new IllegalArgumentException("경로가 존재하지 않습니다.");
    }

    @Override
    protected void validateMiddlePath(Board board, List<Position> root) {
    }
}
