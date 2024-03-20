package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("위치")
public class SquareTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void createPosition() {
        // when & then
        assertThatCode(() -> Square.of(File.a, Rank.THREE)).doesNotThrowAnyException();
    }

    @DisplayName("위치는 인덱스만큼 전진된 위치를 반환한다.")
    @Test
    void forwardByIndex() {
        // given
        Square square = Square.of(File.a, Rank.TWO);
        int index = 3;

        // when
        Square actual = square.forward(index);

        // then
        assertThat(actual).isEqualTo(Square.of(File.a, Rank.FIVE));
    }
}
