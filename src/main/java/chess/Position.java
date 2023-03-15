package chess;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Position {

    private final File file;
    private final Rank rank;

    public Position(final int file, final int rank) {
        this.file = File.from(file);
        this.rank = Rank.from(rank);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    private enum Rank {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8);

        private final int value;

        Rank(final int value) {
            this.value = value;
        }

        static Rank from(int value) {
            return Arrays.stream(Rank.values())
                         .filter(it -> it.value == value)
                         .findAny()
                         .orElseThrow(NoSuchElementException::new);
        }
    }

    // TODO : NoSuch 메세지 넣을지 말지 고민하기
    private enum File {
        A(1),
        B(2),
        C(3),
        D(4),
        E(5),
        F(6),
        G(7),
        H(8);

        private final int value;

        File(final int value) {
            this.value = value;
        }

        static File from(int value) {
            return Arrays.stream(File.values())
                         .filter(it -> it.value == value)
                         .findAny()
                         .orElseThrow(NoSuchElementException::new);
        }
    }
}
