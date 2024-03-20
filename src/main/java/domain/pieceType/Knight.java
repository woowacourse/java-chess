package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {

    private static final int[][] vectors = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1},
            {-2, -1}};
    private static final PieceType PIECE_TYPE = PieceType.KNIGHT;

    public Knight(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        final File sourceFile = source.getFile();
        final File targetFile = target.getFile();

        final int fileSub = targetFile.subtract(sourceFile);

        final Rank sourceRank = source.getRank();
        final Rank targetRank = target.getRank();

        final int rankSub = targetRank.subtrack(sourceRank);

        final boolean canMove = Arrays.stream(vectors)
                .anyMatch(vector -> vector[0] == fileSub && vector[1] == rankSub);

        if (canMove) {
            return List.of(target);
        }

        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
