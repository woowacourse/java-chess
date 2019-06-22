package chess.domain.piece;

public class Rook extends ContinuousMovementPiece {
    private static final Piece BLACK_ROOK = new Rook(TeamType.BLACK, PieceType.ROOK);
    private static final Piece WHITE_ROOK = new Rook(TeamType.WHITE, PieceType.ROOK);

    private Rook(TeamType teamType, PieceType pieceType) {
        super(teamType, pieceType, DirectionType.straightDirections());
    }

    public static Piece of(TeamType teamType) {
        return (teamType == TeamType.BLACK) ? BLACK_ROOK : WHITE_ROOK;
    }
}
