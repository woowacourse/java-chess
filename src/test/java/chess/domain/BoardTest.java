package chess.domain;

import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = Board.create(new HashMap<>());
    }

    @Test
    @DisplayName("체스판은 64개의 칸으로 이루어져 있다.")
    void board64Size() {
        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @Test
    @DisplayName("출발지와 도착지가 같으면 예외가 발생한다")
    void throwExcpetionWhenSamePosition() {
        final Position source = Position.from("a3");
        final Position target = Position.from("a3");

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발지와 도착지는 같을 수 없습니다");
    }
}
