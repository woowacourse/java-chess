package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.PositionGenerator;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BishopTest {

    /*
        .......*
        *.....*.
        .*...*..
        ..*.*...
        ...S....
        ..*.*...
        .*...*..
        *.....*.
     */

    private static Stream<Arguments> provideValidPosition() {
        return Stream.of(
                Arguments.of(File.A, Rank.ONE),
                Arguments.of(File.B, Rank.TWO),
                Arguments.of(File.C, Rank.THREE),
                Arguments.of(File.E, Rank.FIVE),
                Arguments.of(File.F, Rank.SIX),
                Arguments.of(File.G, Rank.SEVEN),
                Arguments.of(File.H, Rank.EIGHT),
                Arguments.of(File.A, Rank.SEVEN),
                Arguments.of(File.B, Rank.SIX),
                Arguments.of(File.C, Rank.FIVE),
                Arguments.of(File.E, Rank.THREE),
                Arguments.of(File.F, Rank.TWO),
                Arguments.of(File.G, Rank.ONE)
        );
    }

    private static Stream<Arguments> provideInvalidPosition() {
        return Stream.of(
                Arguments.of(File.A, Rank.TWO),
                Arguments.of(File.A, Rank.THREE),
                Arguments.of(File.A, Rank.FOUR),
                Arguments.of(File.A, Rank.FIVE),
                Arguments.of(File.A, Rank.SIX),
                Arguments.of(File.A, Rank.EIGHT),

                Arguments.of(File.B, Rank.ONE),
                Arguments.of(File.B, Rank.THREE),
                Arguments.of(File.B, Rank.FOUR),
                Arguments.of(File.B, Rank.FIVE),
                Arguments.of(File.B, Rank.SEVEN),
                Arguments.of(File.B, Rank.EIGHT),
                Arguments.of(File.B, Rank.EIGHT),

                Arguments.of(File.C, Rank.ONE),
                Arguments.of(File.C, Rank.TWO),
                Arguments.of(File.C, Rank.FOUR),
                Arguments.of(File.C, Rank.SIX),
                Arguments.of(File.C, Rank.SEVEN),
                Arguments.of(File.C, Rank.EIGHT),

                Arguments.of(File.D, Rank.ONE),
                Arguments.of(File.D, Rank.TWO),
                Arguments.of(File.D, Rank.THREE),
                Arguments.of(File.D, Rank.FIVE),
                Arguments.of(File.D, Rank.SIX),
                Arguments.of(File.D, Rank.SEVEN),
                Arguments.of(File.D, Rank.EIGHT),

                Arguments.of(File.E, Rank.ONE),
                Arguments.of(File.E, Rank.TWO),
                Arguments.of(File.E, Rank.FOUR),
                Arguments.of(File.E, Rank.SIX),
                Arguments.of(File.E, Rank.SEVEN),
                Arguments.of(File.E, Rank.EIGHT),

                Arguments.of(File.F, Rank.ONE),
                Arguments.of(File.F, Rank.THREE),
                Arguments.of(File.F, Rank.FOUR),
                Arguments.of(File.F, Rank.FIVE),
                Arguments.of(File.F, Rank.SEVEN),
                Arguments.of(File.F, Rank.EIGHT),
                Arguments.of(File.F, Rank.EIGHT),

                Arguments.of(File.G, Rank.TWO),
                Arguments.of(File.G, Rank.THREE),
                Arguments.of(File.G, Rank.FOUR),
                Arguments.of(File.G, Rank.FIVE),
                Arguments.of(File.G, Rank.SIX),
                Arguments.of(File.G, Rank.EIGHT),

                Arguments.of(File.H, Rank.ONE),
                Arguments.of(File.H, Rank.TWO),
                Arguments.of(File.H, Rank.THREE),
                Arguments.of(File.H, Rank.FOUR),
                Arguments.of(File.H, Rank.FIVE),
                Arguments.of(File.H, Rank.SIX),
                Arguments.of(File.H, Rank.SEVEN)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidPosition")
    @DisplayName("목적지가 대각선 경로에 있는 경우 움직일 수 있다.")
    void canMove_Diagonal_True(File file, Rank rank) {
        Piece piece = new Bishop(Color.WHITE);
        Position source = PositionGenerator.generate(File.D, Rank.FOUR);
        Position target = PositionGenerator.generate(file, rank);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPosition")
    @DisplayName("목적지가 대각선 경로에 없는 경우 움직일 수 없다.")
    void canMove_Diagonal_False(File file, Rank rank) {
        Piece piece = new Bishop(Color.WHITE);
        Position source = PositionGenerator.generate(File.D, Rank.FOUR);
        Position target = PositionGenerator.generate(file, rank);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
