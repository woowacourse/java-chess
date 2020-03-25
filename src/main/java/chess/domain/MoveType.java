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
        if (source.calculateRankDistance(target) == source.calculateFileDistance(target)) {
            return CROSS;
        }
        if ((source.calculateFileDistance(target) == 1 && source.calculateRankDistance(target) == 2)
                || (source.calculateRankDistance(target) == 1 && source.calculateFileDistance(target) == 2)) {
            return KNIGHT;
        }
        return NONE;
    }
}
