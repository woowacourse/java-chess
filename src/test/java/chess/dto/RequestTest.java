package chess.dto;

import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RequestTest {

    @Test
    @DisplayName("2개가 아닌 인수가 올 경우 예외가 발생한다.")
    void throwsExceptionWithArgumentSize() {
        String input = "move a1 a2 a3";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Request.of(input));
    }

    @Test
    @DisplayName("올바른 입력이 오면 입력을 올바르게 파싱한다.")
    void ofTest() {
        String input = "move a1 a2";
        Request request = Request.of(input);

        assertThat(request.getArguments().contains(Point.of("a1")) &&
                request.getArguments().contains(Point.of("a2")))
                .isTrue();
    }
}
