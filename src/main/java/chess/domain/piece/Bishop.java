package chess.domain.piece;

import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(PieceType pieceType, Team team) {
        super(pieceType, team);
    }

    @Override
    public boolean movable(Position source, Position target) {
        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return fileGap == rankGap;
    }
}
