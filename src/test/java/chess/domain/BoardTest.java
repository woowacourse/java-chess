package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

@DisplayName("보드")
class BoardTest {

    @Test
    @DisplayName("해당 위치에 기물이 존재하지 않는지 확인한다.")
    void existPiece() {
        Position position = Position.from("b3");
        Board board = new Board(Map.of());

        assertThat(board.isNotExistPiece(position)).isTrue();
    }
}
