package chess.piece;

import static chess.Movement.LEFT_UP;
import static chess.Movement.RIGHT_UP;
import static chess.Movement.UP;
import static chess.Movement.UP_UP;
import static chess.Row.TWO;

import chess.Color;
import chess.Movement;
import chess.Position;
import chess.board.Board;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public static List<List<Movement>> allMovements = new ArrayList<>();

    static {
        allMovements.add(List.of(UP));
        allMovements.add(List.of(UP_UP));
        allMovements.add(List.of(LEFT_UP));
        allMovements.add(List.of(RIGHT_UP));
    }

    public Pawn(final Color color) {
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
                if (start.row() != TWO && movements.contains(UP_UP)) {
                    throw new IllegalArgumentException("처음 움직이는 폰만 앞으로 두 칸을 이동할 수 있습니다.");
                }
                return root;
            }
        }
        throw new IllegalArgumentException("경로가 존재하지 않습니다.");
    }

    @Override
    protected void validateMiddlePath(Board board, List<Position> root) {
    }
}
