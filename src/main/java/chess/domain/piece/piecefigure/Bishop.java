package chess.domain.piece.piecefigure;

import chess.domain.piece.pieceinfo.DirectionType;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

public class Bishop extends ContinuousMovementPiece {
    private static final Piece BLACK_BISHOP = new Bishop(TeamType.BLACK, PieceType.BISHOP);
    private static final Piece WHITE_BISHOP = new Bishop(TeamType.WHITE, PieceType.BISHOP);

    private Bishop(final TeamType teamType, final PieceType pieceType) {
        super(teamType, pieceType, DirectionType.diagonalStraightDirections());
    }

    public static Piece of(final TeamType teamType) {
        return teamType == TeamType.WHITE ? WHITE_BISHOP : BLACK_BISHOP;
    }
}
