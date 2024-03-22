package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.position.Row;

public class Pawn extends Piece {
    private static final int FIRST_STEP_LIMIT = 2;
    private static final int STEP_LIMIT = 1;

    public Pawn(PieceColor color, Position position) {
        super(color, position);
    }

    private boolean isBackward(Position source, Position target) {
        if (getColor() == PieceColor.BLACK) {
            return target.isLowerThan(source);
        }
        return target.isUpperThan(source);
    }

    private boolean isFirstStep(Position position) {
        if (getColor() == PieceColor.BLACK) {
            return position.isSameRow(Row.SEVEN);
        }
        return position.isSameRow(Row.TWO);
    }

    @Override
    public void move(Position target) {

    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }
}
