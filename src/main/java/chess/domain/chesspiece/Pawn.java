package chess.domain.chesspiece;

import chess.domain.File;
import chess.domain.Side;
import chess.domain.Square;

import java.util.ArrayList;
import java.util.List;

public final class Pawn extends Piece {
    private static final List<Pawn> BLACK_PAWNS = new ArrayList<>();
    private static final List<Pawn> WHITE_PAWNS = new ArrayList<>();

    static {
        addPawns(BLACK_PAWNS, Side.BLACK);
        addPawns(WHITE_PAWNS, Side.WHITE);
    }

    private boolean atInitialPosition;

    private Pawn(Side side) {
        super(side);
        atInitialPosition = true;
    }

    private static void addPawns(final List<Pawn> pawns, final Side side) {
        for (int i = 0; i < File.values().length; i++) {
            pawns.add(new Pawn(side));
        }
    }

    public static List<Pawn> of(final Side side) {
        if (side == Side.BLACK) {
            return List.copyOf(BLACK_PAWNS);
        }
        return List.copyOf(WHITE_PAWNS);
    }

    @Override
    public boolean isMovable(final Square from, final Square to, final Piece piece) {
        return canMoveForward(from, to, piece) ||
                isCatchable(from, to, piece);
    }

    private boolean canMoveForward(final Square from, final Square to, final Piece piece) {
        boolean targetInMovableRange = from.inPawnsMovableRange(to, side);
        if (atInitialPosition) {
            targetInMovableRange = from.inPawnsInitialMovableRange(to, side);
        }
        return piece.isEmpty() && targetInMovableRange;

    }

    private boolean isCatchable(final Square from, final Square to, final Piece piece) {
        return this.isOppositeSide(piece) && from.inPawnsCatchableRange(to, side);
    }

    public void move() {
        atInitialPosition = false;
    }
}
