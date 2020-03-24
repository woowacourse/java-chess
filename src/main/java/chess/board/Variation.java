package chess.board;

import java.util.Objects;

public class Variation {
    private final int fileVariation;
    private final int rankVariation;

    public Variation(final int fileVariation, final int rankVariation) {
        this.fileVariation = fileVariation;
        this.rankVariation = rankVariation;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Variation variation = (Variation) o;
        return fileVariation == variation.fileVariation &&
                rankVariation == variation.rankVariation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileVariation, rankVariation);
    }
}
