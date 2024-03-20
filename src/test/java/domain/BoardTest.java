package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @DisplayName("기물의 시작 위치를 배치한 Board 인스턴스를 생성한다.")
    @Test
    void createBoard() {
        // When
        Board board = BoardInitializer.init();

        // Then
        assertThat(board).isNotNull();
    }
}
