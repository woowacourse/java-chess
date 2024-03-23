package domain.game;

import java.math.BigInteger;
import java.util.Objects;

public class ChessVector {

    private final int fileVector;
    private final int rankVector;

    public ChessVector(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public ChessVector toUnitVector() {
        return new ChessVector(fileVector / gcd(), rankVector / gcd());
    }

    private int gcd() {
        return BigInteger.valueOf(this.fileVector).gcd(BigInteger.valueOf(rankVector)).intValue();
    }

    public int getFileVector() {
        return fileVector;
    }

    public int getRankVector() {
        return rankVector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessVector that = (ChessVector) o;
        return fileVector == that.fileVector && rankVector == that.rankVector;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileVector, rankVector);
    }
}
