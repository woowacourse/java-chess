package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Path;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@DisplayName("Pawn은 ")
class PawnTest {

    @Test
    @DisplayName("검은색일 때 대문자 이름을 반환한다.")
    void getBlackNameTest() {
        // given
        Piece pawn = new BlackPawn();

        // when
        String name = pawn.getName();

        // then
        assertThat(name).isEqualTo("P");
    }

    @Test
    @DisplayName("흰색일 때 소문자 이름을 반환한다.")
    void getWhiteNameTest() {
        // given
        Piece pawn = new WhitePawn();

        // when
        String name = pawn.getName();

        // then
        assertThat(name).isEqualTo("p");
    }

    @ParameterizedTest
    @MethodSource("isMovableInitialRowBlackPawnTestCase")
    @DisplayName("검은색일 때 초기 위치일 경우 아래, 오른쪽 아래 대각선, 왼쪽 아래 대각선으로 한 칸, 아래로 두 칸 이동 할 수 있다.")
    void isMovableInitialRowBlackPawnTest(Path path) {
        // given
        Piece pawn = new BlackPawn();
        Position start = Position.of(7, 5);

        // when
        boolean result = pawn.isMovablePath(start, path);

        // then
        assertThat(result).isTrue();
    }

    @ParameterizedTest
    @MethodSource("isMovableInitialRowWhitePawnTestCase")
    @DisplayName("흰색일 때 초기 위치일 경우 위, 오른쪽 위 대각선, 왼쪽 위 대각선으로 한 칸, 위로 두 칸 이동 할 수 있다.")
    void isMovableInitialRowWhitePawnTest(Path path) {
        // given
        Piece pawn = new WhitePawn();
        Position start = Position.of(2, 5);

        // when
        boolean result = pawn.isMovablePath(start, path);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("기본점수가 1점이다.")
    void getScoreTest() {
        // given
        Piece pawn = new WhitePawn();

        // when
        double score = pawn.getScore();

        // then
        assertThat(score).isEqualTo(1);
    }

    static Stream<Arguments> isMovableInitialRowBlackPawnTestCase() {
        return Stream.of(
                Arguments.of(new Path(Position.of(7, 5), Position.of(6, 5))),
                Arguments.of(new Path(Position.of(7, 5), Position.of(6, 4))),
                Arguments.of(new Path(Position.of(7, 5), Position.of(6, 6))),
                Arguments.of(new Path(Position.of(7, 5), Position.of(5, 5)))
        );
    }

    static Stream<Arguments> isMovableInitialRowWhitePawnTestCase() {
        return Stream.of(
                Arguments.of(new Path(Position.of(2, 5), Position.of(3, 5))),
                Arguments.of(new Path(Position.of(2, 5), Position.of(3, 4))),
                Arguments.of(new Path(Position.of(2, 5), Position.of(3, 6))),
                Arguments.of(new Path(Position.of(2, 5), Position.of(4, 5)))
        );
    }
}
