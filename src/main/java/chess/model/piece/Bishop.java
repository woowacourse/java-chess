package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Bishop extends Piece {
    private static final Map<Side, Bishop> CACHE = Arrays.stream(Side.values())
            .collect(toMap(identity(), Bishop::new));

    private Bishop(Side side) {
        super(side);
    }

    public static Bishop from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "b";
        }
        return "B";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Movement movement = target.calculateMovement(source);
        if (movement.isDiagonal()) {
            return movement.findStraightPath(source);
        }
        return List.of();
    }
}
