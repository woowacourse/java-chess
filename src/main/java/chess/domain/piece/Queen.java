package chess.domain.piece;

public class Queen extends ContinuousMovementPiece {
    private static final Piece BLACK_QUEEN = new Queen(TeamType.BLACK, PieceType.QUEEN);
    private static final Piece WHITE_QUEEN = new Queen(TeamType.WHITE, PieceType.QUEEN);

    private Queen(final TeamType teamType, final PieceType pieceType) {
        super(teamType, pieceType, DirectionType.allDirections());
    }

    public static Piece of(final TeamType teamType) {
        return teamType == TeamType.WHITE ? WHITE_QUEEN : BLACK_QUEEN;
    }
}
