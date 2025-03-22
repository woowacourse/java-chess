package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.LEFT;
import static chess.Movement.LEFT_DOWN;
import static chess.Movement.LEFT_UP;
import static chess.Movement.RIGHT;
import static chess.Movement.RIGHT_DOWN;
import static chess.Movement.RIGHT_UP;
import static chess.Movement.UP;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        for (Movement movement : List.of(LEFT, RIGHT, DOWN, UP, LEFT_DOWN, LEFT_UP, RIGHT_UP, RIGHT_DOWN)) {
            for (int i = 1; i < 8; i++) {
                List<Movement> movements = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    movements.add(movement);
                }
                allMovements.add(movements);
            }
        }
    }
    public Queen(final Color color) {
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
        root.removeFirst();
        root.removeLast();
        for (Position position : root) {
            if (board.isPieceExists(position)) {
                throw new IllegalArgumentException("중간에 다른 기물을 뛰어넘을 수 없습니다.");
            }
        }
    }
}
