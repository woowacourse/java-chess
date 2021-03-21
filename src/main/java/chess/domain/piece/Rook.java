package chess.domain.piece;

import chess.domain.piece.rule.Direction;
import chess.domain.piece.rule.Distance;
import chess.domain.piece.rule.Score;

public class Rook extends Piece {
    private static final Rook BLACK_ROOK = new Rook(Owner.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Owner.WHITE);

    public Rook(final Owner owner) {
        super(
                owner,
                new Score(5.0d),
                Direction.straightDirections(),
                Distance.SEVEN,
                "R"
        );
    }

    public static Rook getInstanceOf(final Owner owner) {
        if (owner.equals(Owner.BLACK)) {
            return BLACK_ROOK;
        }

        if (owner.equals(Owner.WHITE)) {
            return WHITE_ROOK;
        }

        throw new IllegalArgumentException("Invalid Rook");
    }
}
