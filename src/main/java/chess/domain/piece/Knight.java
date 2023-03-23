package chess.domain.piece;

import chess.domain.camp.TeamColor;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(final PieceType pieceType, final TeamColor teamColor) {
        super(pieceType, teamColor);
    }

    @Override
    public boolean canMove(final Position source, final Position target, final Piece targetPiece) {
        final int rankGap = target.getRank() - source.getRank();
        final int fileGap = target.getFile() - source.getFile();
        return Math.abs(rankGap * fileGap) == 2;
    }
}
