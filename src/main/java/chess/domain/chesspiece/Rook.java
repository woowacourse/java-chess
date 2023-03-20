package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {
    private static final List<Rook> blackRooks = new ArrayList<>();
    private static final List<Rook> whiteRooks = new ArrayList<>();

    static {
        addRooks(blackRooks, Side.BLACK);
        addRooks(whiteRooks, Side.WHITE);
    }


    private Rook(final Side side) {
        super(side);
    }

    private static void addRooks(final List<Rook> rooks, final Side side) {
        for (int i = 0; i < 2; i++) {
            rooks.add(new Rook(side));
        }
    }

    public static List<Rook> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackRooks);
        }
        return List.copyOf(whiteRooks);
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isNotSameSide(piece) && from.inLine(to);
    }
}
