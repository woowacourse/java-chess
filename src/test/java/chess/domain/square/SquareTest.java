package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("스퀘어")
class SquareTest {

    @DisplayName("문자열로 Sqaure를 생성할 수 있다.")
    @Test
    void createSquare() {
        //given
        String givenSquare = "a4";

        int expectedFileIndex = 0;
        int expectedRankIndex = 3;

        //when
        Square square = Square.from(givenSquare);

        //then
        assertThat(square.getFileIndex()).isEqualTo(expectedFileIndex);
        assertThat(square.getRankIndex()).isEqualTo(expectedRankIndex);
    }

    @DisplayName("유효하지 않은 파일, 랭크이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"I8", "a9", "I9"})
    void invalidSquare(String square) {
        //given & when & then
        assertThatThrownBy(() -> Square.from(square))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
