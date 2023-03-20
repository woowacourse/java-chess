package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final List<Knight> blackKnights = new ArrayList<>();
    private static final List<Knight> whiteKnights = new ArrayList<>();

    static {
        addKnights(blackKnights, Side.BLACK);
        addKnights(whiteKnights, Side.WHITE);
    }


    private Knight(final Side side) {
        super(side);
    }

    private static void addKnights(final List<Knight> Knights, final Side side) {
        for (int i = 0; i < 2; i++) {
            Knights.add(new Knight(side));
        }
    }

    public static List<Knight> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackKnights);
        }
        return List.copyOf(whiteKnights);
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.inLShape(to);
    }
}
