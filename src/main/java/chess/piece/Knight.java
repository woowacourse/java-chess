package chess.piece;

import static chess.Movement.DOWN_DOWN_LEFT;
import static chess.Movement.DOWN_DOWN_RIGHT;
import static chess.Movement.LEFT_LEFT_DOWN;
import static chess.Movement.LEFT_LEFT_UP;
import static chess.Movement.RIGHT_RIGHT_DOWN;
import static chess.Movement.RIGHT_RIGHT_UP;
import static chess.Movement.UP_UP_LEFT;
import static chess.Movement.UP_UP_RIGHT;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        allMovements.add(List.of(UP_UP_RIGHT));
        allMovements.add(List.of(UP_UP_LEFT));
        allMovements.add(List.of(DOWN_DOWN_RIGHT));
        allMovements.add(List.of(DOWN_DOWN_LEFT));
        allMovements.add(List.of(LEFT_LEFT_UP));
        allMovements.add(List.of(LEFT_LEFT_DOWN));
        allMovements.add(List.of(RIGHT_RIGHT_UP));
        allMovements.add(List.of(RIGHT_RIGHT_DOWN));
    }
    public Knight(final Color color) {
        super(color);
    }

    @Override
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
