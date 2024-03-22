package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class Queen extends Piece {
    private static final Map<Side, Queen> CACHE = Arrays.stream(Side.values())
            .collect(Collectors.toMap(identity(), Queen::new));

    private Queen(Side side) {
        super(side);
    }

    public static Queen from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "q";
        }
        return "Q";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Movement movement = target.calculateMovement(source);
        if (canMove(movement)) {
            return movement.findStraightPath(source);
        }
        return List.of();
    }

    private boolean canMove(Movement movement) {
        return movement.isOrthogonal() || movement.isDiagonal();
    }
}
