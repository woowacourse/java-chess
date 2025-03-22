package chess.piece;

import chess.board.ChessBoard;
import chess.board.Color;
import chess.board.Movement;
import chess.board.Position;
import java.util.List;
import java.util.Set;

public class King implements Piece {

    private static final Set<List<Movement>> PATHS = Set.of(
            List.of(Movement.UP),
            List.of(Movement.DOWN),
            List.of(Movement.RIGHT),
            List.of(Movement.LEFT),
            List.of(Movement.LEFT_DOWN),
            List.of(Movement.LEFT_UP),
            List.of(Movement.RIGHT_DOWN),
            List.of(Movement.RIGHT_UP)
    );

    private final Color color;

    public King(Color color) {
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
