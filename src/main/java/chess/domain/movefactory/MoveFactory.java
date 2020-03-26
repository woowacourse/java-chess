package chess.domain.movefactory;

import chess.domain.Position;

public class MoveFactory {
    public static MoveType of(Position source, Position target) {
        if (source.isSameRank(target) || source.isSameFile(target)) {
            return new Straight(source, target);
        }
        if (Math.abs(source.calculateRankDistance(target)) == Math.abs(source.calculateFileDistance(target))) {
            return new Cross(source, target);
        }
//        if ((Math.abs(source.calculateFileDistance(target)) == 1 && Math.abs(source.calculateRankDistance(target)) == 2)
//                || (Math.abs(source.calculateRankDistance(target)) == 1 && Math.abs(source.calculateFileDistance(target)) == 2)) {
//            return KNIGHT;
//        }
//        return NONE;
        return new Straight(source, target);
    }
}
