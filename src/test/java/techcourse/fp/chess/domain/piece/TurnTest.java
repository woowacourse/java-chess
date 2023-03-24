package techcourse.fp.chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TurnTest {

    @DisplayName("현재 turn과 다른 색이면 ture를 반환한다.")
    @Test
    void test1() {
        // given
        final Turn turn = Turn.createByStartTurn(Color.WHITE);
        // when
        final boolean actual = turn.isNotSameTurn(Color.BLACK);
        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("현재 turn과 같은 색이면 false 반환한다.")
    @Test
    void test2() {
        // given
        final Turn turn = Turn.createByStartTurn(Color.WHITE);
        // when
        final boolean actual = turn.isNotSameTurn(Color.WHITE);
        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("nextTurn을 통해 다음 턴으로 진행한다.")
    @Test
    void next_turn() {
        // given
        final Turn turn = Turn.createByStartTurn(Color.WHITE);
        // when
        turn.nextTurn();
        final boolean actual = turn.isNotSameTurn(Color.WHITE);
        // then
        assertThat(actual).isTrue();
    }
}
