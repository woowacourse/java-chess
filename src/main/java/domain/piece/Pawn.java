package domain.piece;

import domain.position.Position;
import domain.position.Rank;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        if (isFirstMovement(source)) {
            if (isBlack()) {
                return (source.isDown(target) && source.isLegalRankStep(target, 1, 2))
                        || source.isRightDown(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(
                        target, 1)
                        || source.isLeftDown(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(
                        target, 1);
            }
            return (source.isUp(target) && source.isLegalRankStep(target, 1, 2))
                    || source.isRightUp(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(target,
                    1)
                    || source.isLeftUp(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(target,
                    1);
        }
        if (isBlack()) {
            return (source.isDown(target) && source.isLegalRankStep(target, 1))
                    || source.isRightDown(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(target,
                    1)
                    || source.isLeftDown(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(target,
                    1);
        }
        return (source.isUp(target) && source.isLegalRankStep(target, 1))
                || source.isRightUp(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(target, 1)
                || source.isLeftUp(target) && source.isLegalRankStep(target, 1) && source.isLegalFileStep(target, 1);
    }

    // 폰 규칙
    // 1. 첫 움직임의 경우 앞으로 1 또는 2 이동 가능
    // 2. 다음 움직임부터는 앞으로 1 이동 가능
    // 3. 공격은 오른위 또는 왼위 대각선으로 가능

    // 한칸 전진할 때 내편 상대편 기물이 있으면 막힘, 아무것도 없으면 괜찮음
    // 대각선 전진할 때 내편 기물이 있으면 막힘, 상대편 기물이 있으면 괜찮음, 아무것도 없으면 막힘

    private boolean isFirstMovement(Position source) {
        return source.hasRank(Rank.TWO) || source.hasRank(Rank.SEVEN);
    }

    @Override
    public String display() {
        return "P";
    }
}
