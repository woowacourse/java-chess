package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;
import chess.model.position.Path;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Knight extends Piece {
    private static final Map<Side, Knight> CACHE = Arrays.stream(Side.values())
            .collect(toMap(identity(), Knight::new));

    private static final int DISPLACEMENT = 3;

    private Knight(Side side) {
        super(side);
    }

    public static Knight from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public Path findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        Movement movement = target.calculateMovement(source);
        if (canMove(movement)) {
            return new Path(List.of(target));
        }
        return Path.empty();
    }

    private boolean canMove(Movement movement) {
        if (movement.isOrthogonal() || movement.isDiagonal()) {
            return false;
        }
        return movement.hasLengthOf(DISPLACEMENT);
    }
}
