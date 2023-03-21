package chess.dto;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareDtoTest {

    @ParameterizedTest
    @CsvSource({"a1", "h5", "b4", "d2", "h8", "e1", "f6"})
    @DisplayName("올바른 문자열을 입력하면 예외를 발생시키지 않는다.")
    void create_success(String square) {
        Assertions.assertThatCode(() -> SquareDto.of(square))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @CsvSource({"a", "5", "A3", "a9", "n1", "a1a", "h81"})
    @DisplayName("올바르지 않은 문자열을 입력하면 예외를 발생시킨다.")
    void create_fail(String square) {
        Assertions.assertThatThrownBy(() -> SquareDto.of(square))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 보드 위치입니다. 보드는 a~h, 1~8로 구성됩니다.");
    }

    @Test
    @DisplayName("같은 Square를 반환한다.")
    void return_same_square() {
        SquareDto squareDto = SquareDto.of("a4");
        Square square = Square.of(File.A, Rank.FOUR);
        Assertions.assertThat(squareDto.getSquare()).isEqualTo(square);
    }
}
