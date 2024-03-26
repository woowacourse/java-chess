package chess.domain.piece;

import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Direction;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public class King extends UnslidingPiece {

    private static final Position WHITE_INITIAL_POSITION = Position.from("e1");
    private static final Position BLACK_INITIAL_POSITION = Position.from("e8");

    private static final Set<Movement> POSSIBLE_MOVEMENTS = Direction.all()
            .stream()
            .map(Movement::of)
            .collect(Collectors.toUnmodifiableSet());

    public King(final Color color, final Position position) {
        super(color, position);
    }

    public static King ofInitialPosition(final Color color) {
        return new King(color, selectByColor(color, WHITE_INITIAL_POSITION, BLACK_INITIAL_POSITION));
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        validateTarget(movablePositions(chessboard), target);
        return new King(color(), target);
    }

    @Override
    public Set<Position> movablePositions(final Chessboard chessboard) {
        return movablePositions(chessboard, POSSIBLE_MOVEMENTS);
    }
}
