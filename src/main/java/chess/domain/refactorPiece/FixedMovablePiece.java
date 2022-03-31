package chess.domain.refactorPiece;

import chess.domain.piece.attribute.Color;
import chess.domain.refactorBoard.ChessBoard;
import chess.domain.refactorBoard.Direction;
import chess.domain.refactorPosition.Position;
import java.util.List;

public abstract class FixedMovablePiece extends Piece {

    private static final int FIXED_MOVE_SIZE = 1;

    private final List<Direction> directions;

    public FixedMovablePiece(Color color, String name, List<Direction> directions) {
        super(color, name);
        this.directions = directions;
    }

    @Override
    public boolean isMovable(Position from, Position to, ChessBoard chessBoard) {
        return isMovableByFixed(from, to) && chessBoard.isEmptyPosition(to);
    }

    private boolean isMovableByFixed(Position from, Position to) {
        return directions.stream()
                .map(direction -> direction.route(from, to))
                .anyMatch(route -> route.size() == FIXED_MOVE_SIZE);
    }
}
