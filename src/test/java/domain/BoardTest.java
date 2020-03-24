package domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    @DisplayName("게임판을 만드는 테스트")
    void create_Board() {
        List<List<Chess>> chess = new ArrayList<>();
        Board board1 = new Board(chess);
        Board board2 = new Board(chess);
        assertThat(board1).isEqualTo(board2);
    }
}
