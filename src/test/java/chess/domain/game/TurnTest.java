package chess.domain.game;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TurnTest {

    @DisplayName("turn 생성자 정상 동작 확인")
    @Test
    void create_normal_test() {
        assertThat(new Turn(Color.WHITE)).isInstanceOf(Turn.class);
    }

    @DisplayName("turn 생성자에 NONE 컬러가 들어올 시 예외처리")
    @Test
    void create_when_color_none_throw_exception() {
        assertThatThrownBy(() -> new Turn(Color.NONE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("턴은 BLACK이나 WHITE로만 시작할 수 있습니다.");
    }

    @DisplayName("change WHITE일시 BLACK을 반환")
    @Test
    void change_when_white_change_to_black() {
        Turn turn = new Turn(Color.WHITE);
        turn.change();
        assertEquals(turn.getColor(), Color.BLACK);
    }

}