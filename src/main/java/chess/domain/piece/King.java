package chess.domain.piece;

import chess.domain.position.Position;

public class King extends Piece {
    private static final int MAX_FILE_GAP = 1;
    private static final int MAX_RANK_GAP = 1;

    public King(PieceType pieceType, Team team) {
        super(pieceType, team);
    }

    @Override
    public boolean movable(Position source, Position target) {
        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return fileGap <= MAX_FILE_GAP
                && rankGap <= MAX_RANK_GAP;
    }
}
