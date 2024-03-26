package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("스퀘어")
class SquareTest {
    @Test
    @DisplayName("사용자 입력으로 들어온 좌표를 파일과 랭크로 변환한다.")
    void convertUserInputToFileAndRankTest() {
        // given
        Square square = Square.from("b3");
        int expectedFileOrdinal = 1;
        int expectedRankOrdinal = 5;

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(square.getFileOrdinal()).isEqualTo(expectedFileOrdinal);
            softAssertions.assertThat(square.getRankOrdinal()).isEqualTo(expectedRankOrdinal);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "1", "A1", "aa1", "a10"})
    @DisplayName("유효하지 않은 형식으로 스퀘어를 만들 경우 예외가 발생한다.")
    void validatePatternTest(String squareInput) {
        assertThatCode(() -> Square.from(squareInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("문자 1개 숫자 1개를 붙인 위치형식으로 입력해 주세요");
    }

    @Test
    @DisplayName("목적지 까지로의 경로를 생성한다.")
    void generatePathTest() {
        // given
        Square square = Square.from("b3");
        Square target = Square.from("e6");
        List<Square> expected = List.of(
                Square.from("c4"),
                Square.from("d5")
        );

        // when
        List<Square> actual = square.generatePath(target);

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @DisplayName("목적지의 파일과 같은지 확인한다.")
    void isSameFileWithSquareTest() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("b4");

        // when
        boolean isSame = source.isSameFile(target);

        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("다른 파일과 같은지 확인한다.")
    void isSameFileWithOtherFileTest() {
        // given
        Square source = Square.from("b7");
        File file = File.B;

        // when
        boolean isSame = source.isSameFile(file);

        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("목적지의 랭크과 같은지 확인한다.")
    void isSameRankWithSquareTest() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("c7");

        // when
        boolean isSame = source.isSameRank(target);

        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("다른 파일과 같은지 확인한다.")
    void isSameRankWithOtherFileTest() {
        // given
        Square source = Square.from("b7");
        Rank rank = Rank.SEVEN;

        // when
        boolean isSame = source.isSameRank(rank);

        // then
        assertThat(isSame).isTrue();
    }

    @Test
    @DisplayName("다른 스퀘어와 같은 대각선에 있는지 확인한다.")
    void isSameDiagonal() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("c6");

        // when
        boolean sameDiagonal = source.isSameDiagonal(target);

        // then
        assertThat(sameDiagonal).isTrue();
    }

    @Test
    @DisplayName("파일의 거리를 계산한다.")
    void distanceFileFromTest() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("c6");

        // when
        int distance = source.distanceFileFrom(target);

        // then
        assertThat(distance).isEqualTo(1);
    }

    @Test
    @DisplayName("랭크의 거리를 계산한다.")
    void distanceRankFromTest() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("b6");

        // when
        int distance = source.distanceRankFrom(target);

        // then
        assertThat(distance).isEqualTo(1);
    }

    @Test
    @DisplayName("목적지보다 더 위에 있는지 확인한다.")
    void isUpperThanTest() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("b6");

        // when
        boolean upperThan = source.isUpperThan(target);

        // then
        assertThat(upperThan).isTrue();
    }

    @Test
    @DisplayName("목적지보다 더 아래에 있는지 확인한다.")
    void isLowerThanTest() {
        // given
        Square source = Square.from("b7");
        Square target = Square.from("c8");

        // when
        boolean upperThan = source.isLowerThan(target);

        // then
        assertThat(upperThan).isTrue();
    }

    @Test
    @DisplayName("파일 값을 정수로 반환한다.")
    void getFileOrdinal() {
        // given
        Square square = Square.from("b3");
        int expectedFileOrdinal = 1;

        // when & then
        assertThat(square.getFileOrdinal()).isEqualTo(expectedFileOrdinal);
    }

    @Test
    @DisplayName("랭크 값을 정수로 반환한다.")
    void getRankOrdinal() {
        // given
        Square square = Square.from("b3");
        int expectedRankOrdinal = 5;

        // when & then
        assertThat(square.getRankOrdinal()).isEqualTo(expectedRankOrdinal);
    }
}
