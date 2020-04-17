package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    @DisplayName("board는 boardInitializer 타입의 객체를 받아서 생성")
    void initialize() {
        assertThat(Board.of(new DefaultBoardInitializer())).isInstanceOf(Board.class);
    }
}