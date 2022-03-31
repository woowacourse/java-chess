package chess.domain.refactorPiece;

import static chess.domain.refactorBoard.Direction.DOWN;
import static chess.domain.refactorBoard.Direction.DOWN_LEFT;
import static chess.domain.refactorBoard.Direction.DOWN_RIGHT;
import static chess.domain.refactorBoard.Direction.LEFT;
import static chess.domain.refactorBoard.Direction.RIGHT;
import static chess.domain.refactorBoard.Direction.TOP;
import static chess.domain.refactorBoard.Direction.TOP_LEFT;
import static chess.domain.refactorBoard.Direction.TOP_RIGHT;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorBoard.Direction;
import chess.domain.refactorPosition.Position;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {

    private static final int FIXED_MOVE_SIZE = 1;

    private final List<Direction> directions;

    public King(Color color) {
        this(color, "K", Arrays.asList(
                TOP, DOWN, RIGHT, LEFT, TOP_RIGHT, TOP_LEFT, DOWN_RIGHT, DOWN_LEFT));
    }

    public King(Color color, String name, List<Direction> directions) {
        super(color, name);
        this.directions = directions;
    }

    @Override
    public Piece move(Position from, Position to, ChessBoard chessBoard) {
        if (!isMovable(from, to, chessBoard)) {
            throw new IllegalArgumentException("움직일 수 없는 이동입니다.");
        }

        return this;
    }

    public boolean isMovable(Position from, Position to, ChessBoard chessBoard) {
        return isMovableByFixed(from, to)
                && chessBoard.isEmptyPosition(to);
    }

    private boolean isMovableByFixed(Position from, Position to) {
        return directions.stream()
                .map(direction -> direction.route(from, to))
                .anyMatch(route -> route.size() == FIXED_MOVE_SIZE);
    }
}
