package chess.model.piece;

import chess.model.evaluation.CommonValue;
import chess.model.evaluation.PieceValue;
import chess.model.position.Position;
import chess.model.position.Movement;
import chess.model.position.Path;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Queen extends Piece {
    private static final Map<Side, Queen> CACHE = Side.colors().stream()
            .collect(toMap(identity(), Queen::new));

    private Queen(Side side) {
        super(side);
    }

    public static Queen from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public Path findPath(Position source, Position target, Piece targetPiece) {
        Movement movement = target.calculateMovement(source);
        if (canMove(movement)) {
            return Path.makeStraightPath(source, movement);
        }
        return Path.empty();
    }

    private boolean canMove(Movement movement) {
        return movement.isOrthogonal() || movement.isDiagonal();
    }

    @Override
    public PieceValue value() {
        return new CommonValue(9);
    }
}
