package chess.model.piece;

import chess.model.game.CommonValue;
import chess.model.game.PieceValue;
import chess.model.position.ChessPosition;
import chess.model.position.Movement;
import chess.model.position.Path;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class Rook extends Piece {
    private static final Map<Side, Rook> CACHE = Arrays.stream(Side.values())
            .collect(Collectors.toMap(identity(), Rook::new));

    private Rook(Side side) {
        super(side);
    }

    public static Rook from(Side side) {
        return CACHE.get(side);
    }

    @Override
    public Path findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        Movement movement = target.calculateMovement(source);
        if (movement.isOrthogonal()) {
            return Path.makeStraightPath(source, movement);
        }
        return Path.empty();
    }

    @Override
    public PieceValue value() {
        return new CommonValue(5);
    }
}
