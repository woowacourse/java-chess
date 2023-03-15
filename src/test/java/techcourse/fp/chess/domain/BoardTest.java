package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.map;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("")
    @Test
    void boardInitTest() {
        // given
        final Board board = BoardFactory.generate();
        assertThat(board).extracting("board")
                .asInstanceOf(map(Position.class, String.class))
                .size()
                .isEqualTo(64);
    }

    @DisplayName("보드의 사이즈가 8 x 8이 아니라면 생성시 예외를 반환한다.")
    @Test
    void create_fail() {
        assertThatThrownBy(() -> new Board(Map.of()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판의 사이즈는 8 x 8 여야합니다.");
    }
}
