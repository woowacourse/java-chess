package chess.piece;

import java.util.function.Function;

public enum PieceType {

    PAWN(team -> {
        if (team == Team.WHITE) {
            return new WhitePawn();
        }
        return new BlackPawn();
    }),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    QUEEN(Queen::new),
    KING(King::new),
    EMPTY((ignored) -> new EmptyPiece()),
    ;

    private final Function<Team, Piece> function;

    PieceType(final Function<Team, Piece> function) {
        this.function = function;
    }

    public Piece getInstance(final Team team) {
        return this.function.apply(team);
    }
}
