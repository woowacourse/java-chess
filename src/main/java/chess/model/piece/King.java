package chess.model.piece;

import chess.model.position.ChessPosition;
import chess.model.position.Movement;

import java.util.List;

public class King extends Piece {
    private static final int DISPLACEMENT = 1;

    public King(Side side) {
        super(side);
    }

    @Override
    public String getText() {
        if (side.isWhite()) {
            return "k";
        }
        return "K";
    }

    @Override
    public List<ChessPosition> findPath(ChessPosition source, ChessPosition target, Piece targetPiece) {
        checkValidTargetPiece(targetPiece);
        Movement movement = target.calculateMovement(source);
        if (movement.hasSame(DISPLACEMENT)) {
            return List.of(target);
        }
        return List.of();
    }
}
