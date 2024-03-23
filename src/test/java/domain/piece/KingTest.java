package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KingTest {

    /*
        ........
        ........
        ........
        ..***...
        ..*S*...
        ..***...
        ........
        ........
     */
    private static Stream<Arguments> provideFileAndRank() {
        return Stream.of(
                Arguments.of(File.C, Rank.THREE),
                Arguments.of(File.C, Rank.FOUR),
                Arguments.of(File.C, Rank.FIVE),
                Arguments.of(File.D, Rank.THREE),
                Arguments.of(File.D, Rank.FIVE),
                Arguments.of(File.E, Rank.THREE),
                Arguments.of(File.E, Rank.FIVE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideFileAndRank")
    @DisplayName("목적지가 해당 경로 위에 있는 경우 움직일 수 있다.")
    void canMove_True(File file, Rank rank) {
        Piece piece = new King(Color.WHITE);
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(file, rank);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isTrue();
    }

    /*
        ........
        ........
        ........
        ..***...
        ..*S*T..
        ..***...
        ........
        ........
     */
    @Test
    @DisplayName("목적지가 해당 경로 위에 없는 경우 움직일 수 없다.")
    void canMove_False() {
        Piece piece = new King(Color.WHITE);
        Position source = new Position(File.D, Rank.FOUR);
        Position target = new Position(File.F, Rank.FOUR);

        boolean actual = piece.canMove(source, target);

        assertThat(actual).isFalse();
    }
}
