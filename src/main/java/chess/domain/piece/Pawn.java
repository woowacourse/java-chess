package chess.domain.piece;

import static chess.domain.chessboard.attribute.Direction.UP;

import java.util.HashSet;
import java.util.Set;

import chess.domain.chessboard.Chessboard;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Movement;
import chess.domain.piece.attribute.Position;

public class Pawn extends AbstractPawn {

    private static final Set<Movement> POSSIBLE_MOVEMENTS = Set.of(Movement.of(UP));

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Piece move(final Chessboard chessboard, final Position target) {
        Set<Position> possiblePositions = new HashSet<>();
        possiblePositions.addAll(attackablePositions(chessboard));
        possiblePositions.addAll(possiblePositions(chessboard, POSSIBLE_MOVEMENTS));
        validateTarget(possiblePositions, target);
        return new Pawn(color(), target);
    }
}
