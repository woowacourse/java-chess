package chess.model.piece;

import chess.model.game.CommonValue;
import chess.model.game.PieceValue;
import chess.model.position.ChessPosition;
import chess.model.position.Movement;
import chess.model.position.Path;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class Bishop extends Piece {
    private static final Map<Side, Bishop> CACHE = Side.colors().stream()
            .collect(toMap(identity(), Bishop::new));

    private Bishop(Side side) {
        super(side);
    }

    public static Bishop from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public Path findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        Movement movement = target.calculateMovement(source);
        if (movement.isDiagonal()) {
            return Path.makeStraightPath(source, movement);
        }
        return Path.empty();
    }

    @Override
    public PieceValue value() {
        return new CommonValue(3);
    }
}
