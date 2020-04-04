package chess.domain.piece;

import chess.domain.position.Position;

public class Rook extends Piece {

    private static final int MIN_FILE_GAP = 0;
    private static final int MIN_RANK_GAP = 0;

    public Rook(PieceType pieceType, Team team) {
        super(pieceType, team);
    }

    @Override
    public boolean movable(Position source, Position target) {
        int fileGap = Math.abs(source.calculateFileGap(target));
        int rankGap = Math.abs(source.calculateRankGap(target));

        return (fileGap > MIN_FILE_GAP && rankGap == MIN_RANK_GAP)
                || (fileGap == MIN_FILE_GAP && rankGap > MIN_RANK_GAP);
    }
}
