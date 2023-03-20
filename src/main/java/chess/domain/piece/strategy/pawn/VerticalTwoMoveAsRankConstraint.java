package chess.domain.piece.strategy.pawn;

import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

public class VerticalTwoMoveAsRankConstraint implements PawnMoveConstraint {

    private final PiecePosition standard;

    public VerticalTwoMoveAsRankConstraint(final Rank rank) {
        this.standard = PiecePosition.of(rank, File.from(File.MIN));
    }

    @Override
    public void validateConstraint(final PiecePosition source,
                                   final PiecePosition destination) {
        if (!isTwoVerticalMove(source, destination)) {
            return;
        }
        if (standard.rankInterval(source) != 0) {
            throw new IllegalArgumentException(String.format("%s 랭크에서만 두 칸 이동할 수 있습니다.", standard.rankValue()));
        }
    }

    // TODO 중복 발생 ㅠㅠ 이거 추상 골격에도 있습니다.. ㅠㅠ
    protected boolean isTwoVerticalMove(final PiecePosition source, final PiecePosition destination) {
        if (Math.abs(source.rankInterval(destination)) != 2) {
            return false;
        }
        return Math.abs(source.fileInterval(destination)) == 0;
    }
}
