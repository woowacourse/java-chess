package chess.piece;

public abstract class Pawn extends Piece {

    protected Pawn(final Team team) {
        super(team, PieceType.PAWN);
    }
}
