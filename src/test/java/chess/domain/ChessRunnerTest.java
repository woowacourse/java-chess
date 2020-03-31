package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessRunnerTest {
    ChessRunner chessRunner;

    @BeforeEach
    void setUp() {
        chessRunner = new ChessRunner();
    }

    @DisplayName("현재 순서가 아닐 때 에러 메시지 출력")
    @Test
    void validateCurrentTeam() {
        assertThatThrownBy(() -> chessRunner.update("a7", "a5")) //블랙 팀 폰 이동
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 차례가 아닙니다.");
    }

    @DisplayName("이동할 수 없는 곳일 때 에러 메시지 출력")
    @Test
    void validateMovable() {
        assertThatThrownBy(() -> chessRunner.update("a1", "a2")) //화이트 팀 룩 이동
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 곳입니다.");
    }
}