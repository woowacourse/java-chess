package chess.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = Board.init();
    }

    @Test
    @DisplayName("source 위치의 기물이 target 위치로 이동할 수 없으면 예외가 발생한다.")
    void validateUnmovable() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 4);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("이동 경로에 기물이 있으면 예외가 발생한다.")
    void validateBlocked() {
        Position source = new Position(1, 1);
        Position target = new Position(4, 1);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동 경로에 기물이 있습니다.");
    }
}
