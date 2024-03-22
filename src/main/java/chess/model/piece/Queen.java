package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.List;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side);
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
            return movement.findPath(source);
        }
        return List.of();
    }

    private boolean canMove(Movement movement) {
        return movement.isCrossMovement() || movement.isDiagonalMovement();
    }
}
