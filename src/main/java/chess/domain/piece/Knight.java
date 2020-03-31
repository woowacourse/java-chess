package chess.domain.piece;

import chess.domain.position.Position;

public class Knight extends Piece {
    private static final int HORIZONTAL_FILE_GAP = 2;
    private static final int HORIZONTAL_RANK_GAP = 1;
    private static final int VERTICAL_FILE_GAP = 1;
    private static final int VERTICAL_RANK_GAP = 2;

    public Knight(PieceType pieceType, Team team) {
        super(pieceType, team);
    }

    @Override
    public boolean movable(Position source, Position target) {
        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return (fileGap == HORIZONTAL_FILE_GAP && rankGap == HORIZONTAL_RANK_GAP)
                || (fileGap == VERTICAL_FILE_GAP && rankGap == VERTICAL_RANK_GAP);
    }
}
