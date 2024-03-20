package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.List;

public class King extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.KING;

    public King(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        final File sourceFile = source.getFile();
        final File targetFile = target.getFile();

        final int fileVector = sourceFile.subtract(targetFile);

        final Rank sourceRank = source.getRank();
        final Rank targetRank = target.getRank();

        final int rankVector = sourceRank.subtrack(targetRank);

        if (Math.abs(fileVector) < 2 && Math.abs(rankVector) < 2) {
            return List.of(target);
        }
        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
