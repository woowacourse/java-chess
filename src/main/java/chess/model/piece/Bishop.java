package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Side side) {
        super(side);
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
        if (movement.isDiagonalMovement()) {
            return movement.findPath(source);
        }
        return List.of();
    }
}
