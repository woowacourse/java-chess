package chess.domain.movefactory;

import chess.domain.Position;

public class MoveFactory {
    public static MoveType of(Position source, Position target) {
        if (source.isSameRank(target) || source.isSameFile(target)) {
            return new StraightType(source, target);
        }
        if (Math.abs(source.calculateRankDistance(target)) == Math.abs(source.calculateFileDistance(target))) {
            return new CrossType(source, target);
        }
        if ((Math.abs(source.calculateFileDistance(target)) == 1 && Math.abs(source.calculateRankDistance(target)) == 2)
                || (Math.abs(source.calculateRankDistance(target)) == 1 && Math.abs(source.calculateFileDistance(target)) == 2)) {
            return new KnightType(target);
        }
//        return NONE;
        return new StraightType(source, target);
    }
}
