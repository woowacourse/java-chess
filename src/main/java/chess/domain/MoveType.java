package chess.domain;

public enum MoveType {
    STRAIGHT,
    CROSS,
    KNIGHT,
    NONE;

    public static MoveType of(Position source, Position target) {
        if (source.isSameRank(target) || source.isSameFile(target)) {
            return STRAIGHT;
        }
        if (Math.abs(source.calculateRankDistance(target)) == Math.abs(source.calculateFileDistance(target))) {
            return CROSS;
        }
        if ((Math.abs(source.calculateFileDistance(target)) == 1 && Math.abs(source.calculateRankDistance(target)) == 2)
                || (Math.abs(source.calculateRankDistance(target)) == 1 && Math.abs(source.calculateFileDistance(target)) == 2)) {
            return KNIGHT;
        }
        return NONE;
    }
}
