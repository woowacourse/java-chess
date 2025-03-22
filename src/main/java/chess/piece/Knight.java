package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Movement;
import chess.board.Position;
import java.util.List;
import java.util.Set;

public class Knight implements Piece {

    private static final Set<List<Movement>> PATHS = Set.of(
            List.of(Movement.UP_UP_LEFT),
            List.of(Movement.UP_UP_RIGHT),
            List.of(Movement.LEFT_LEFT_UP),
            List.of(Movement.LEFT_LEFT_DOWN),
            List.of(Movement.RIGHT_RIGHT_UP),
            List.of(Movement.RIGHT_RIGHT_DOWN),
            List.of(Movement.DOWN_DOWN_LEFT),
            List.of(Movement.DOWN_DOWN_RIGHT)
    );

    private final Color color;

    public Knight(Color color) {
        this.color = color;
    }

    @Override
    public boolean canMoveToDestination(ChessBoard chessBoard, Position start, Position end) {
        for (List<Movement> path : PATHS) {
            Position current = new Position(start.row(), start.column());
            for (Movement direction : path) {
                if (!chessBoard.isEmpty(current)) {
                    break;
                }
                current = current.move(direction);
            }
            if (current.equals(end)) {
                return chessBoard.isNotSameColor(current, color);
            }
        }
        return false;
    }

    @Override
    public boolean isSameColor(Color color) {
        return this.color == color;

    }
}
