package chess.domain.piece.piecefigure;

import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

public class Knight extends IncontinuousMovementPiece {
    private static final Piece BLACK_KNIGHT = new Knight(TeamType.BLACK, PieceType.KNIGHT);
    private static final Piece WHITE_KNIGHT = new Knight(TeamType.WHITE, PieceType.KNIGHT);

    private Knight(final TeamType teamType, final PieceType pieceType) {
        super(teamType, pieceType, DirectionType.knightDirections());
    }

    public static Piece of(final TeamType teamType) {
        return teamType == TeamType.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT;
    }
}
