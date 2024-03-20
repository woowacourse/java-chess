package domain.pieceType;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Color;
import domain.File;
import domain.Rank;
import domain.Square;
import domain.piece.King;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KingTest {

    @DisplayName("킹은 상하좌우 대각선으로 한칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource(value = "squareArguments")
    void canMove(final Square source, final Square target) {
        final King king = new King(Color.BLACK);

        // when
        final List<Square> canMove = king.calculatePath(source, target);

        // then
        assertThat(canMove).hasSize(1).containsExactly(target);
    }

    static Stream<Arguments> squareArguments() {
        return Stream.of(
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.C)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FOUR, File.E)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.D)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.D)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.C)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.THREE, File.E)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.C)),
                Arguments.of(new Square(Rank.FOUR, File.D), new Square(Rank.FIVE, File.E))
        );
    }

    static Square makeSquare(final int x, final int y) {
        final Rank rank = Arrays.stream(Rank.values())
                .filter(r -> r.getIndex() == x)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        final File file = Arrays.stream(File.values())
                .filter(f -> f.getIndex() == x)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return new Square(rank, file);
    }
}
