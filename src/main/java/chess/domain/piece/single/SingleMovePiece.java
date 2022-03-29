package chess.domain.piece.single;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.AbstractPiece;
import java.util.List;

public abstract class SingleMovePiece extends AbstractPiece {

    private static final int MOVABLE_COUNT = 1;

    private final List<Direction> moveDirections;

    protected SingleMovePiece(Color color, String name, List<Direction> moveDirections) {
        super(color, name);
        this.moveDirections = moveDirections;
    }

    @Override
    public final boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        return moveDirections.stream()
                .map(direction -> direction.route(source, target))
                .anyMatch(route -> route.size() == MOVABLE_COUNT);
    }
}
