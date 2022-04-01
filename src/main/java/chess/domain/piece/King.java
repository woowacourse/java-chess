package chess.domain.piece;

import java.util.Arrays;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedKing;

public final class King extends Piece {
    private static final String NAME = "k";
    private static final double SCORE = 0;

    public King(Color color) {
        super(color, NAME, SCORE, new StartedKing());
    }

    public static boolean canCreate(Piece piece) {
        return Arrays.stream(Color.values())
            .anyMatch(color -> new King(color).isSame(piece));
    }

    private boolean isSame(Piece piece) {
        return this.equals(piece);
    }
}
