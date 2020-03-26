package chess.domain.board;

import chess.domain.board.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SquareTest {

    @Test
    @DisplayName("칸 캐싱했는지 확인")
    void checkSquareCache() {
        assertThat(Square.of("a1")).isEqualTo(Square.of("a1"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a9", "f0", "jkl", "j3"})
    @DisplayName("잘못된 값이 of에 들어갔을 때 예외 발생")
    void validLocation(String location) {
        assertThatThrownBy(() -> Square.of(location))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("더한 값이 캐싱된 값이면 캐싱 값 리턴, 아니면 본인 리턴")
    @Test
    void canAddTest() {
        Square square = Square.of("a2");
        assertThat(square.addIfInBoundary(1, 1)).isEqualTo(Square.of("b3"));
        assertThat(square.addIfInBoundary(-1, 1)).isEqualTo(square);
    }

    @DisplayName("같은 File의 Square인지 확인")
    @Test
    void isSameFile() {
        Square squareA2 = Square.of("a2");
        Square squareA3 = Square.of("a3");
        Square squareB2 = Square.of("b2");
        assertThat(squareA2.isJustSameFile(squareA3)).isTrue();
        assertThat(squareA2.isJustSameFile(squareB2)).isFalse();
    }

}
