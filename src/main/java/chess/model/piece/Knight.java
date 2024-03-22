package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.List;

public class Knight extends Piece {
    private static final int DISPLACEMENT = 3;

    public Knight(Side side) {
        super(side);
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
