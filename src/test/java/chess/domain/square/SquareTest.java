package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayName("스퀘어")
class SquareTest {
    @Test
    @DisplayName("사용자 입력으로 들어온 좌표를 파일과 랭크로 변환한다.")
    void convertUserInputToFileAndRank() {
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
    void validatePattern(String squareInput) {
        assertThatCode(() -> Square.from(squareInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("문자 1개 숫자 1개를 붙인 위치형식으로 입력해 주세요");
    }
}
