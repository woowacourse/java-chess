package chess.domain;

import static chess.domain.PositionFixture.A1;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.B2;
import static chess.domain.PositionFixture.C3;
import static chess.domain.PositionFixture.D4;
import static chess.domain.PositionFixture.E1;
import static chess.domain.PositionFixture.E2;
import static chess.domain.PositionFixture.E3;
import static chess.domain.PositionFixture.E4;
import static chess.domain.PositionFixture.E5;
import static chess.domain.PositionFixture.F1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.game.File;
import chess.domain.game.Position;
import chess.domain.game.Rank;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PositionTest {

    static Stream<Arguments> generatePath() {
        return Stream.of(
                Arguments.of(A1, List.of(D4, C3, B2)),
                Arguments.of(E1, List.of(E4, E3, E2))
        );
    }

    @Test
    void 같은_File과_같은_Rank일_경우_동일한_객체가_반환된다() {
        //given
        Position position2 = Position.of(File.A, Rank.ONE);

        //when
        boolean result = position2 == A1;

        //then
        assertThat(result)
                .isTrue();
    }

    @Test
    void 포지션의_랭크_차이_계산_테스트() {
        //given
        //when
        int result = A1.getRankDifference(A3);

        //then
        assertThat(result)
                .isEqualTo(2);
    }

    @Test
    void 포지션의_파일_차이_계산_테스트() {
        //given
        //when
        int result = A1.getFileDifference(F1);

        //then
        assertThat(result)
                .isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TWO", "F, SIX"})
    void 한_직선상의_Postion_은_Path_를_만들_수_있다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        assertDoesNotThrow(() -> A1.createStraightPath(destination));
    }

    @ParameterizedTest
    @CsvSource(value = {"B, THREE", "F, FIVE"})
    void 한_직선_위의_Position_이_아니라면_Path_빈_Path_를_반환한다(File file, Rank rank) {
        //given
        Position destination = Position.of(file, rank);

        //expect
        List<Position> result = A1.createStraightPath(destination);

        assertThat(result)
                .isEmpty();
    }

    @ParameterizedTest
    @MethodSource("generatePath")
    void 이동_경로상의_포지션들을_반환한다(Position destination, List<Position> expected) {
        //given
        List<Position> result = E5.createStraightPath(destination);

        //expected
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }
}
