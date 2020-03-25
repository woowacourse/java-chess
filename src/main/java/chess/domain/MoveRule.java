package chess.domain;

public class MoveRule {
    public static void validateStraightMove(Position source, Position target) {
        if (source.isSameRank(target) || source.isSameFile(target)) {
            return;
        }
        throw new IllegalArgumentException("이동할수 없는 칸입니다.");
    }
}
