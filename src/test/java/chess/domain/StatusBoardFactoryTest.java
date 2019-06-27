package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusBoardFactoryTest {
    @Test
    void 초기_상태() {
        Board initBoard = BoardFactory.create();
        StatusBoard statusBoard = StatusBoardFactory.create(initBoard);
        StatusBoard expectedStatusBoard = new StatusBoard(38, 38);

        assertThat(statusBoard).isEqualTo(expectedStatusBoard);
    }
}