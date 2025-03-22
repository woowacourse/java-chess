package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.LEFT;
import static chess.Movement.RIGHT;
import static chess.Movement.UP;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        for (Movement movement : List.of(LEFT, RIGHT, DOWN, UP)) {
            for (int i = 1; i < 8; i++) {
                List<Movement> movements = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    movements.add(movement);
                }
                allMovements.add(movements);
            }
        }
    }
    public Rook(final Color color) {
        super(color);
    }

    @Override
    protected List<Position> findRoot(Board board, Position start, Position goal) {
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
        throw new IllegalArgumentException("해당 기물은 해당 위치로 이동할 수 없습니다.");
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

    @Override
    public String toString() {
        return "룩";
    }
}
