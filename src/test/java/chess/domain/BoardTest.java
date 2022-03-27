package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @Test
    @DisplayName("초기화된 체스판인지 확인")
    void isInitializedTrue() {
        Board board = new Board(new BoardInitializer());

        assertThat(board.isInitialized(new BoardInitializer())).isTrue();
    }

    @Test
    @DisplayName("체스 말 이동 후 초기화된 체스판이 아닌 것을 확인")
    void isInitializedFalse() {
        Board board = new Board(new BoardInitializer());
        board.move(Position.from("a2"), Position.from("a3"));

        assertThat(board.isInitialized(new BoardInitializer())).isFalse();
    }

    @Test
    @DisplayName("존재하지 않는 말을 선택한 경우 예외발생")
    void move() {
        final Board emptyBoard = new Board(HashMap::new);

        assertThatThrownBy(() -> emptyBoard.move(Position.from("e5"), Position.from("e7")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }
}
