package chess.domain.pieces;

import chess.domain.moving.PawnMoving;
import chess.domain.position.Position;
import chess.domain.position.Row;

public final class Pawn extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "7";
    private static final String WHITE_TEAM_ROW = "2";

    public Pawn(final Position position) {
        super(position, Information.PAWN, new PawnMoving());
    }

    public static Pawn white(final int col) {
        return new Pawn(new Position(Row.location(WHITE_TEAM_ROW), col));
    }

    public static Pawn black(final int col) {
        return new Pawn(new Position(Row.location(BLACK_TEAM_ROW), col));
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
