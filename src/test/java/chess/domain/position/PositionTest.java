package chess.domain.position;

import static chess.domain.position.Position.from;
import static chess.fixture.PositionFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class PositionTest {

    @Test
    void Rank_와_File_을_받아_정상적으로_생성된다() {
        // expect
        assertThatNoException().isThrownBy(() -> Position.of(File.A, Rank.ONE));
    }

    @Test
    void command_를_입력받아_Position_을_반환한다() {
        // given
        final Position position = from("a2");

        // expect
        assertThat(position).isEqualTo(Position.of(File.A, Rank.TWO));
    }

    @Test
    void 잘못된_커맨드를_입력받으면_예외를_던진다() {
        // expect
        assertThatThrownBy(() -> from("a0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치값입니다.");
    }

    @Test
    void 입력받는_포지션과의_파일_차이를_반환한다() {
        // given
        final Position source = Position.of(File.A, Rank.FOUR);
        final Position target = Position.of(File.G, Rank.FIVE);

        // when
        final int result = source.calculateFileGap(target);

        // then
        assertThat(result).isEqualTo(-6);
    }

    @Test
    void 입력받는_포지션과의_랭크_차이를_반환한다() {
        // given
        final Position source = Position.of(File.A, Rank.FOUR);
        final Position target = Position.of(File.G, Rank.FIVE);

        // when
        final int result = source.calculateRankGap(target);

        // then
        assertThat(result).isEqualTo(-1);
    }

    @ParameterizedTest(name = "시작과 끝이 직선인 경우 포지션 사이의 값들을 반환한다. 시작: {0}, 도착: {1}")
    @MethodSource("betweenStraightSource")
    void 시작과_끝이_직선인_경우_포지션_사이의_값들을_반환한다(
            final Position source,
            final Position target,
            final List<Position> result
    ) {
        // expect
        final List<Position> positions = source.between(target);
        assertThat(positions).containsAll(result);
    }

    static Stream<Arguments> betweenStraightSource() {
        return Stream.of(
                Arguments.of(A1, A8, List.of(A2, A3, A4, A5, A6, A7)),
                Arguments.of(A1, H1, List.of(B1, C1, D1, E1, F1, G1)),
                Arguments.of(A1, A2, Collections.emptyList()),
                Arguments.of(A1, B1, Collections.emptyList()),
                Arguments.of(H2, A2, List.of(G2, F2, E2, D2, C2, B2)),
                Arguments.of(B8, B6, List.of(B7))
        );
    }

    @ParameterizedTest(name = "시작과 끝이 대각선인 경우 포지션 사이의 값들을 반환한다. 시작: {0}, 도착: {1}")
    @MethodSource("betweenDiagonalSource")
    void 시작과_끝이_대각선인_경우_포지션_사이의_값들을_반환한다(
            final Position source,
            final Position target,
            final List<Position> result
    ) {
        // expect
        final List<Position> positions = source.between(target);
        assertThat(positions).containsAll(result);
    }

    static Stream<Arguments> betweenDiagonalSource() {
        return Stream.of(
                Arguments.of(A1, H8, List.of(B2, C3, D4, E5, F6, G7)),
                Arguments.of(A8, H1, List.of(B7, C6, D5, E4, F3, G2)),
                Arguments.of(A1, B2, Collections.emptyList()),
                Arguments.of(B2, C1, Collections.emptyList()),
                Arguments.of(H2, F4, List.of(G3)),
                Arguments.of(D4, B2, List.of(C3))
        );
    }

    @ParameterizedTest(name = "나이트 이동의 경우 빈 컬렉션을 반환한다. 시작: {0}, 도착: {1}")
    @MethodSource("betweenKnightSource")
    void 나이트_이동의_경우_빈_컬렉션을_반환한다(
            final Position source,
            final Position target,
            final List<Position> result
    ) {
        // expect
        final List<Position> positions = source.between(target);
        assertThat(positions).containsAll(result);
    }

    static Stream<Arguments> betweenKnightSource() {
        return Stream.of(
                Arguments.of(D4, C2, Collections.emptyList()),
                Arguments.of(D4, B3, Collections.emptyList()),
                Arguments.of(D4, B5, Collections.emptyList()),
                Arguments.of(D4, C6, Collections.emptyList()),
                Arguments.of(D4, E6, Collections.emptyList()),
                Arguments.of(D4, F5, Collections.emptyList()),
                Arguments.of(D4, F3, Collections.emptyList()),
                Arguments.of(D4, E2, Collections.emptyList())
        );
    }

    @ParameterizedTest(name = "입력받은 파일과 같은 파일인지 확인한다. 대상: ONE, 입력: {0}, 결과: {1}")
    @CsvSource({"ONE, true", "TWO, false"})
    void 입력받은_파일과_같은_랭크인지_확인한다(final Rank rank, final boolean result) {
        // given
        final Position source = Position.of(File.A, Rank.ONE);

        // expect
        assertThat(source.isSameRank(rank)).isEqualTo(result);
    }
}
