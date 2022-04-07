package console.controller;

import chess.domain.board.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RequestTest {

    @Test
    @DisplayName("입력이 오면 입력을 올바르게 파싱한다.")
    void ofTest() {
        String input = "move a1 a2";
        Request request = Request.of(input);

        assertThat(request.getArguments().contains(Point.of("a1")) &&
                request.getArguments().contains(Point.of("a2")))
                .isTrue();
    }
}
