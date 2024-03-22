package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class Knight extends Piece {
    private static final Map<Side, Knight> CACHE = Arrays.stream(Side.values())
            .collect(Collectors.toMap(identity(), Knight::new));

    private static final int DISPLACEMENT = 3;

    private Knight(Side side) {
        super(side);
    }

    public static Knight from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "n";
        }
        return "N";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Movement movement = target.calculateMovement(source);
        if (canMove(movement)) {
            return List.of(target);
        }
        return List.of();
    }

    private boolean canMove(Movement movement) {
        if (movement.isOrthogonal() || movement.isDiagonal()) {
            return false;
        }
        return movement.hasLengthOf(DISPLACEMENT);
    }
}
