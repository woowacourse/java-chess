package domain.movement;

import java.math.BigInteger;
import java.util.Objects;

public class ChessVector {
    private final int fileVector;
    private final int rankVector;

    public ChessVector(final int fileVector, final int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    /**
    * gcd(최대 공약수)를 구하는 이유: 단위 벡터를 구하기 위해 사용됩니다.
    * fileVector와 rankVector의 gcd로 양 쪽을 나누어, 방향성을 가진 최소 길이의 벡터를 구합니다.
    * 단위벡터를 구하는 이유: 두 Position을 통해 단위 벡터를 구한뒤, Direction에 정의된 방향이 있는지 확인합니다.
    */

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
