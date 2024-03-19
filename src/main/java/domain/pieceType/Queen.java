package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;

public class Queen extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.QUEEN;

    public Queen(final Color color) {
        super(color);
    }

    @Override
    public boolean canMove(final Square source, final Square target) {
        final File sourceFile = source.getFile();
        final File targetFile = target.getFile();

        final int fileGqp = sourceFile.gap(targetFile);

        final Rank sourceRank = source.getRank();
        final Rank targetRank = target.getRank();

        final int rankGap = sourceRank.gap(targetRank);

        return fileGqp == rankGap || fileGqp == 0 || rankGap == 0;
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
