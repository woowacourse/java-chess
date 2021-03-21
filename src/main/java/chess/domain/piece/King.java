package chess.domain.piece;

import chess.domain.piece.rule.Direction;
import chess.domain.piece.rule.Distance;
import chess.domain.piece.rule.Score;

public class King extends Piece {
    private static final King BLACK_KING = new King(Owner.BLACK);
    private static final King WHITE_KING = new King(Owner.WHITE);

    public King(final Owner owner) {
        super(
                owner,
                Score.KING,
                Direction.allDirections(),
                Distance.ONE,
                "K"
        );
    }

    public static King getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_KING;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_KING;
        }

        throw new IllegalArgumentException("Invalid King");
    }
}