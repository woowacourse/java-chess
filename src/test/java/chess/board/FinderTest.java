package chess.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.fixture.FixturePosition.A1;
import static chess.fixture.FixturePosition.A2;
import static chess.fixture.FixturePosition.A3;
import static chess.fixture.FixturePosition.A4;
import static chess.fixture.FixturePosition.A5;
import static chess.fixture.FixturePosition.A6;
import static chess.fixture.FixturePosition.A7;
import static chess.fixture.FixturePosition.A8;
import static chess.fixture.FixturePosition.B1;
import static chess.fixture.FixturePosition.B2;
import static chess.fixture.FixturePosition.B6;
import static chess.fixture.FixturePosition.C1;
import static chess.fixture.FixturePosition.C2;
import static chess.fixture.FixturePosition.C3;
import static chess.fixture.FixturePosition.C5;
import static chess.fixture.FixturePosition.D1;
import static chess.fixture.FixturePosition.D3;
import static chess.fixture.FixturePosition.D4;
import static chess.fixture.FixturePosition.E1;
import static chess.fixture.FixturePosition.E4;
import static chess.fixture.FixturePosition.F1;
import static chess.fixture.FixturePosition.F5;
import static chess.fixture.FixturePosition.G1;
import static chess.fixture.FixturePosition.H1;

class FinderTest {

    @ParameterizedTest(name = "{0}과 {1}사이의 경로는 {2}")
    @MethodSource("routes")
    void 두_지점_사이_경로를_반환한다(final Position from, final Position to, final List<Position> expected) {
        //given
        List<Position> route = Finder.findRoute(from, to);

        //when & then
        Assertions.assertThat(route).containsAnyElementsOf(expected);
    }

    @Test
    void 갈_수_없는_경로인_경우_빈_배열을_반환한다() {
        Assertions.assertThat(Finder.findRoute(A1, C5))
                .isEmpty();
    }

    static Stream<Arguments> routes() {
        return Stream.of(
                Arguments.of(A1, A8, List.of(A2, A3, A4, A5, A6, A7)),
                Arguments.of(A1, H1, List.of(B1, C1, D1, E1, F1, G1)),
                Arguments.of(F5, C2, List.of(E4, D3)),
                Arguments.of(D4, A7, List.of(B6, C5)),
                Arguments.of(A1, C3, List.of(B2)),
                Arguments.of(A1, B2, List.of()),
                Arguments.of(A1, A1, List.of())
        );
    }
}
