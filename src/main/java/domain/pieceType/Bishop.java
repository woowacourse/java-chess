package domain.pieceType;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import java.util.List;
import java.util.stream.Stream;

public class Bishop extends Piece {

    private static final PieceType PIECE_TYPE = PieceType.BISHOP;

    public Bishop(final Color color) {
        super(color);
    }

    @Override
    public List<Square> calculatePath(final Square source, final Square target) {
        final File sourceFile = source.getFile();
        final File targetFile = target.getFile();

        final int fileSub = sourceFile.subtract(targetFile);

        final Rank sourceRank = source.getRank();
        final Rank targetRank = target.getRank();

        final int rankSub = sourceRank.subtrack(targetRank);

        if (Math.abs(fileSub) == Math.abs(rankSub)) {
            return List.of();
        }

        final int fileVector = fileSub / Math.abs(fileSub);
        final int rankVector = rankSub / Math.abs(rankSub);

        final Square nextSquare = source.next(rankVector, fileVector);

        final int length = Math.max(Math.abs(fileSub), Math.abs(rankSub));

        return Stream.iterate(source.next(rankVector, fileVector),
                        i -> i.next(rankVector, fileVector))
                .limit(length)
                .toList();
    }

    @Override
    public PieceType getPieceType() {
        return PIECE_TYPE;
    }
}
