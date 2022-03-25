package chess.domain;

import chess.domain.state.Direction;

public class LocationDiff {

    private final int diffFile;
    private final int diffRank;

    public LocationDiff(int diffFile, int diffRank) {
        checkSameLocation(diffFile, diffRank);
        this.diffFile = diffFile;
        this.diffRank = diffRank;
    }

    private void checkSameLocation(int diffFile, int diffRank) {
        if (diffFile == 0 && diffRank == 0) {
            throw new IllegalArgumentException("[ERROR] 제자리로 움직일 수 없습니다.");
        }
    }

    public Direction computeDirection() {
        int gcd = Math.abs(gcd(diffFile, diffRank));
        return Direction.of(diffFile / gcd, diffRank / gcd);
    }

    public int computeDistance() {
        return Math.abs(gcd(diffFile, diffRank));
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}

