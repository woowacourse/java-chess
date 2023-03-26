package chess.domain.position;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public final class Path {

    private static final int ZERO_DISTANCE = 0;
    private final int fileUnit;
    private final int rankUnit;

    private Path(final int fileUnit, final int rankUnit) {
        this.fileUnit = fileUnit;
        this.rankUnit = rankUnit;
    }

    public static Path of(Position source, Position target) {
        int fileDistance = source.computeFileDistance(target);
        int rankDistance = source.computeRankDistance(target);

        int unitValue = computeUnitValue(fileDistance, rankDistance);

        int fileUnit = fileDistance / unitValue;
        int rankUnit = rankDistance / unitValue;

        return new Path(fileUnit, rankUnit);
    }

    private static int computeUnitValue(final int fileDistance, final int rankDistance) {
        final var file = BigInteger.valueOf(fileDistance);
        final var rank = BigInteger.valueOf(rankDistance);
        BigInteger gcd = file.gcd(rank);

        return gcd.intValue();
    }

    public boolean isDiagonal() {
        return Math.abs(fileUnit) == Math.abs(rankUnit);
    }

    public boolean isStraight() {
        return fileUnit == ZERO_DISTANCE || rankUnit == ZERO_DISTANCE;
    }

    public Set<Position> computePath(Position source, Position target) {
        Set<Position> positions = new HashSet<>();
        while (!source.equals(target)) {
            source = source.moveTo(fileUnit, rankUnit);
            positions.add(source);
        }
        return positions;
    }
}
