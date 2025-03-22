package chess.piece;

import static chess.Movement.LEFT_DOWN;
import static chess.Movement.LEFT_UP;
import static chess.Movement.RIGHT_DOWN;
import static chess.Movement.RIGHT_UP;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        for (Movement movement : List.of(LEFT_UP, RIGHT_UP, RIGHT_DOWN, LEFT_DOWN)) {
            for (int i = 1; i < 8; i++) {
                List<Movement> movements = new ArrayList<>();
                for (int j = 0; j < i; j++) {
                    movements.add(movement);
                }
                allMovements.add(movements);
            }
        }
    }

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public void validateMovable(Board board, Position start, Position goal) {
        List<Position> root = findRoot(start, goal);
        validateMiddlePath(board, root);
        validateSameColorPieceOnGoal(board, goal);
    }

    private static void validateMiddlePath(Board board, List<Position> root) {
        root.removeFirst();
        root.removeLast();
        for (Position position : root) {
            if (board.isPieceExists(position)) {
                throw new IllegalArgumentException("중간에 다른 기물을 뛰어넘을 수 없습니다.");
            }
        }
    }

    /**
     * 제자리로 움직이는 경우 TODO
     */
    private List<Position> findRoot(Position start, Position goal) {
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

    private void validateSameColorPieceOnGoal(Board board, Position goal) {
        if (board.sameColorPieceExists(goal, color)) {
            throw new IllegalArgumentException("상대편의 말만 잡을 수 있습니다.");
        }
    }
}
