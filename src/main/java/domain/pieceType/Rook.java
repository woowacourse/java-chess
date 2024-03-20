package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.List;
import java.util.stream.Stream;

public class Rook extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.ROOK;

    public Rook(final Color color) {
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

        if (fileSub == 0 || rankSub == 0) {
            final int fileVector = fileSub == 0 ? 0 : fileSub / Math.abs(fileSub);
            final int rankVector = rankSub == 0 ? 0 : rankSub / Math.abs(rankSub);

            final int length = Math.max(Math.abs(fileSub), Math.abs(rankSub));

            return Stream.iterate(source.next(rankVector, fileVector),
                            i -> i.next(rankVector, fileVector))
                    .limit(length)
                    .toList();
        }

        return List.of();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
