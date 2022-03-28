package chess.piece;

import chess.game.Direction;
import chess.game.Position;
import java.util.Arrays;

public class King extends AbstractPiece {

    private static final int SCORE = 0;

    public King(final Color color) {
        super(Name.KING, color);
    }

    @Override
    public boolean canMove(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .anyMatch(direction -> direction.canKingMove(from, to));
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
