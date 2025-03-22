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

    }
}
