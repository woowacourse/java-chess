package chess.domain.piece.strategy.constraint;

import chess.domain.piece.position.File;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

public class TwoVerticalMoveAsRankConstraint implements MoveConstraint {

    private final Rank rank;

    public TwoVerticalMoveAsRankConstraint(final Rank rank) {
        this.rank = rank;
    }

    @Override
    public boolean satisfy(final Path path) {
        if (!isTwoVerticalMove(path)) {
            return true;
        }
        final PiecePosition standard = PiecePosition.of(rank, File.from(File.MIN));
        return standard.rankInterval(path.source()) == 0;
    }

    private boolean isTwoVerticalMove(final Path path) {
        if (Math.abs(path.rankInterval()) != 2) {
            return false;
        }
        return Math.abs(path.fileInterval()) == 0;
    }
}
