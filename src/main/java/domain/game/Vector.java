package domain.game;

import java.math.BigInteger;

public record Vector(
        int file,
        int rank
) {
    public Vector toUnitVector() {
        return new Vector(file / gcd(), rank / gcd());
    }

    private int gcd() {
        return BigInteger.valueOf(file)
                .gcd(BigInteger.valueOf(rank))
                .intValue();
    }
}
