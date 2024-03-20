package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {

    private static final int[][] blackVectors = new int[][]{{0, -2}, {0, -1}, {1, -1}, {-1, -1}};
    private static final int[][] whiteVectors = new int[][]{{0, 2}, {0, 1}, {-1, 1}, {1, 1}};

    private static final PieceType PIECE_TYPE = PieceType.PAWN;

    public Pawn(final Color color) {
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

        if (color == Color.BLACK) {
            int n = 0;
            if (sourceRank != Rank.SEVEN) {
                n = 1;
            }

            final boolean canMove = Arrays.stream(blackVectors)
                    .skip(n)
                    .anyMatch(vector -> vector[0] == fileSub && vector[1] == rankSub);
            if (canMove) {
                return List.of(target);
            }
            return List.of();
        }
        int n = 0;
        if (sourceRank != Rank.TWO) {
            n = 1;
        }
        final boolean canMove = Arrays.stream(whiteVectors)
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
