package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.File;
import chess.domain.piece.position.Path;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

public class VerticalTwoMoveAsRankConstraint implements PawnMoveConstraint {

    private final PiecePosition standard;

    public VerticalTwoMoveAsRankConstraint(final Rank rank) {
        this.standard = PiecePosition.of(rank, File.from(File.MIN));
    }

    @Override
    public void validateConstraint(final Path path) {
        if (!path.isTwoVerticalMove()) {
            return;
        }
        if (standard.rankInterval(path.source()) != 0) {
            throw new IllegalArgumentException(String.format("%s 랭크에서만 두 칸 이동할 수 있습니다.", standard.rankValue()));
        }
    }
}
