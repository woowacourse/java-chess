package chess.domain.piece.piecefigure;

import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

public class Pawn extends IncontinuousMovementPiece {
    private static final Piece BLACK_KING = new King(TeamType.BLACK, PieceType.KING);
    private static final Piece WHITE_KING = new King(TeamType.WHITE, PieceType.KING);

    private King(final TeamType teamType, final PieceType pieceType) {
        super(teamType, pieceType, DirectionType.allDirections());
    }

    public static Piece of(final TeamType teamType) {
        return teamType == TeamType.WHITE ? WHITE_KING : BLACK_KING;
    }
}
