package chess.piece;

import static chess.Movement.DOWN;
import static chess.Movement.DOWN_DOWN;
import static chess.Movement.LEFT;
import static chess.Movement.RIGHT;
import static chess.Movement.UP;
import static chess.Movement.UP_UP;
import static chess.Row.SEVEN;
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
        allMovements.add(List.of(DOWN));
        allMovements.add(List.of(DOWN_DOWN));
        allMovements.add(List.of(LEFT, UP));
        allMovements.add(List.of(RIGHT, UP));
        allMovements.add(List.of(LEFT, DOWN));
        allMovements.add(List.of(RIGHT, DOWN));
    }

    public Pawn(final Color color) {
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
                if (color.isWhite() && (movements.contains(DOWN) || movements.contains(DOWN_DOWN))) {
                    break;
                }
                if (color.isBlack() && (movements.contains(UP) || movements.contains(UP_UP))) {
                    break;
                }
                if ((start.row() != TWO) && movements.contains(UP_UP) ||
                        (start.row() != SEVEN) && movements.contains(DOWN_DOWN)) {
                    throw new IllegalArgumentException("처음 움직이는 폰만 앞으로 두 칸을 이동할 수 있습니다.");
                }
                if ((movements.contains(LEFT) || movements.contains(RIGHT)) && board.isDifferentColorPieceNotExists(start, goal)) {
                    throw new IllegalArgumentException("상대 기물이 있는 경우에만 대각선으로 이동할 수 있습니다.");
                }
                return root;
            }
        }
        throw new IllegalArgumentException("해당 기물은 해당 위치로 이동할 수 없습니다.");
    }

    @Override
    protected void validateMiddlePath(Board board, List<Position> root) {
    }

    @Override
    public String toString() {
        return "폰";
    }
}
