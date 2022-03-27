package chess.domain.piece.pawn;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

public final class WhitePawn extends Pawn {

    private static final int INIT_RANK = 1;
    private static final int RANK_FORWARD_DIRECTION = 1;

    private static final String DISPLAY = "♗";

    WhitePawn(Position position) {
        super(Color.WHITE, position);
    }

    WhitePawn(int fileIdx) {
        this(Position.of(fileIdx, INIT_RANK));
    }

    @Override
    protected int initRank() {
        return INIT_RANK;
    }

    @Override
    protected int forwardRankDiff(int rankDiff) {
        return rankDiff * RANK_FORWARD_DIRECTION;
    }

    @Override
    public String display() {
        return DISPLAY;
    }

    @Override
    public String toString() {
        return "WhitePawn{position=" + position + '}';
    }
}
