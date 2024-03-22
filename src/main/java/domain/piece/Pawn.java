package domain.piece;

import domain.position.Position;
import domain.position.Rank;
import java.util.Map;

public class Pawn extends AbstractPiece {
    private static final Map<Color, Rank> INITIAL_RANK = Map.of(Color.WHITE, Rank.TWO, Color.BLACK, Rank.SEVEN);

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public void validateMovement(Position source, Position target, Piece other) {
        if (color().isWhite()) {
            validateWhiteMovement(source, target, other);
            return;
        }
        validateBlackMovement(source, target, other);
    }

    private void validateBlackMovement(Position source, Position target, Piece other) {
        if (source.isLowerRankThan(target)) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
        validateCommon(source, target, other);
    }

    private void validateWhiteMovement(Position source, Position target, Piece other) {
        int rankGap = source.calculateRankGap(target);
        int fileGap = source.calculateFileGap(target);

        if (rankGap == 0 && fileGap == 0) { // todo 추상화하면서 상위 계층으로 이동
            throw new IllegalArgumentException(String.format("%s은 수평, 수직 방향으로만 이동할 수 있습니다.",
                    this.getClass().getSimpleName()));
        }
        if (source.isUpperRankThan(target)) {
            throw new IllegalArgumentException("폰은 뒤로 이동할 수 없습니다.");
        }
        validateCommon(source, target, other);
    }

    private void validateCommon(Position source, Position target, Piece other) { // todo 메서드명 변경
        int moveCount = source.calculateDistance(target);

        if (source.isStraight(target)) {
            validateMovementTwice(source, moveCount);
            validateEmpty(other);
            return;
        }
        if (source.isDiagonal(target)) {
            validateMovementOnce(moveCount);
            validateDifferentColor(other);
            return;
        }
        throw new IllegalArgumentException("잘못된 방향으로 이동하고 있습니다.");
    }

    private void validateMovementTwice(Position source, int moveCount) {
        if (moveCount > 2) { // 움직이는 횟수 확인 1 or 2
            throw new IllegalArgumentException("폰은 직선 방향으로 1칸 또는 2칸, 대각 방향으로 1칸만 이동할 수 있습니다.");
        }
        if (moveCount == 2) { // 2이면 초기 위치인지 확인
            Rank initialRank = INITIAL_RANK.get(color());
            if (!source.isAtSameRank(initialRank)) {
                throw new IllegalArgumentException("초기 위치가 아닌 폰은 2칸 이동할 수 없습니다.");
            }
        }
    }

    private void validateMovementOnce(int moveCount) {
        if (moveCount != 1) { // 움직이는 횟수 확인 1
            throw new IllegalArgumentException("폰은 직선 방향으로 1칸 또는 2칸, 대각 방향으로 1칸만 이동할 수 있습니다.");
        }
    }

    private void validateEmpty(Piece other) {
        if (!other.color().isNeutrality()) {
            throw new IllegalArgumentException("앞에 말이 있어서 이동할 수 없습니다.");
        }
    }

    private void validateDifferentColor(Piece other) {
        if (this.color() == other.color()) {
            throw new IllegalArgumentException("같은 팀의 말을 잡을 수 없습니다.");
        }

        if (other.color().isNeutrality()) {
            throw new IllegalArgumentException("대각선 방향에 상대 말이 없어서 이동할 수 없습니다.");
        }
    }

    @Override
    public Type type() {
        return Type.PAWN;
    }
}
