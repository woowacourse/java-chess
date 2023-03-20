package chess.domain.chesspiece;

import chess.domain.Side;
import chess.domain.Square;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final List<Bishop> blackBishops = new ArrayList<>();
    private static final List<Bishop> whiteBishops = new ArrayList<>();

    static {
        addBishops(blackBishops, Side.BLACK);
        addBishops(whiteBishops, Side.WHITE);
    }

    private Bishop(final Side side) {
        super(side);
    }

    private static void addBishops(final List<Bishop> bishops, final Side side) {
        for (int i = 0; i < 2; i++) {
            bishops.add(new Bishop(side));
        }
    }

    public static List<Bishop> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(blackBishops);
        }
        return List.copyOf(whiteBishops);
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return from.inDiagonal(to) && isNotSameSide(piece);
    }
}
