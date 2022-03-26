package chess.domain;

import chess.domain.board.Board;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @Test
    @DisplayName("존재하지 않는 말을 선택한 경우 예외발생")
    void move() {
        final Board emptyBoard = new Board(HashMap::new);

        assertThatThrownBy(() -> emptyBoard.move(Position.from("e5"), Position.from("e7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }
}
