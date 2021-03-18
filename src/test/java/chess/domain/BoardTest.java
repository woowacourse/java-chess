package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @DisplayName("보드 초기화")
    @Test
    void init() {
        assertThatCode(BoardInitializer::init).doesNotThrowAnyException();
    }

    @DisplayName("팀의 포인트 합산")
    @Test
    void point() {
        Board board = BoardInitializer.init();
        assertThat(board.calculateTotalPoint(Team.WHITE)).isEqualTo(38);
    }

    @DisplayName("같은 세로줄에 위치한 폰의 개수만큼 점수 차감")
    @Test
    void updatePawnPoint() {
        Board board = BoardInitializer.init();
        board.movePiece(Position.of("d2"), Position.of("e3"));
        board.movePiece(Position.of("f2"), Position.of("e4"));
        assertThat(board.updatePawnPoint(Team.WHITE)).isEqualTo(1.5);
    }
}
