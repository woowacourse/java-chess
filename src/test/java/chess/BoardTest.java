package chess;

import chess.Board;
import chess.File;
import chess.Position;
import chess.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {

    @DisplayName("8x8의 보드판이 생성되는지 확인한다.")
    @Test
    void construct_board() {
        Board board = new Board();

        assertThat(board.getBoard().size()).isEqualTo(64);
    }

    @DisplayName("선택한 위치에 기물이 없으면 예외를 발생 시킨다.")
    @Test
    void move_none_exception() {
        Board board = new Board();

        assertThatThrownBy(() -> board.move(Position.of(Rank.THREE, File.D), Position.of(Rank.FOUR, File.D)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 선택한 위치에 기물이 없습니다.");
    }

    @DisplayName("상대편 기물을 선택하면 예외를 발생 시킨다.")
    @Test
    void move_other_exception() {
        Board board = new Board();

        assertThatThrownBy(() -> board.move(Position.of(Rank.TWO, File.D), Position.of(Rank.FOUR, File.D)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 상대편 기물은 선택 할 수 없습니다.");
    }
}
