package chess.domain;

import chess.domain.exception.IllegalPieceMoveException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PositionTest {

    static Stream<Arguments> generatePath() {
        return Stream.of(
                Arguments.of(A1, List.of(D4, C3, B2)),
                Arguments.of(E1, List.of(E4, E3, E2))
        );
    }

    @Test
    void 같은_File과_같은_Rank일_경우_동일한_객체가_반환된다() {
        //given
        Position position1 = A1;
        Position position2 = Position.of(File.A, Rank.ONE);

        //when
        boolean result = position2 == position1;

        //then
        assertThat(result)
                .isTrue();
    }

    @Test
    void 포지션의_랭크_차이_계산_테스트() {
        //given
        Position position1 = A1;
        Position position2 = A3;

        //when
        int result = position1.calculateRankDifference(position2);

        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    void 포지션의_파일_차이_계산_테스트() {
        //given
        Position position1 = A1;
        Position position2 = F1;

        //when
        int result = position1.calculateFileDifference(position2);

        //then
        assertThat(result).isEqualTo(5);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, TWO", "F, SIX"})
    void 한_직선상의_Postion_은_Path_를_만들_수_있다(File file, Rank rank) {
        //given
        Position origin = A1;
        Position destination = Position.of(file, rank);

        //expect
        assertDoesNotThrow(() -> origin.createStraightPath(destination));
    }

    @ParameterizedTest
    @CsvSource(value = {"B, THREE", "F, FIVE"})
    void 한_직선_위의_Position_이_아니라면_Path_를_만들_수_없다(File file, Rank rank) {
        //given
        Position origin = A1;
        Position destination = Position.of(file, rank);

        //expect
        assertThatThrownBy(() -> origin.createStraightPath(destination))
                .isInstanceOf(IllegalPieceMoveException.class);
    }

    @ParameterizedTest
    @MethodSource("generatePath")
    void 이동_경로상의_포지션들을_반환한다(Position destination, List<Position> expected) {
        //given
        Position origin = E5;
        List<Position> result = origin.createStraightPath(destination);

        //expected
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }
}
