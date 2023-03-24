package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("첫 차례는 백팀의 차례이다.")
    void first_turn_is_white() {
        Turn turn = Turn.getFirstTurn();

        assertThat(turn.isWhite()).isTrue();
        assertThat(turn.isBlack()).isFalse();
    }

    @Test
    @DisplayName("두번째 차례는 흑팀의 차례이다.")
    void second_turn_is_black() {
        Turn turn = Turn.getFirstTurn();
        Turn nextTurn = Turn.next(turn);

        assertThat(nextTurn.isBlack()).isTrue();
        assertThat(nextTurn.isWhite()).isFalse();
    }

    @Test
    @DisplayName("세번째 차례는 다시 백팀의 차례이다.")
    void third_turn_is_white() {
        Turn firstTurn = Turn.getFirstTurn();
        Turn secondTurn = Turn.next(firstTurn);
        Turn thirdTurn = Turn.next(secondTurn);

        assertThat(thirdTurn.isWhite()).isTrue();
        assertThat(thirdTurn.isBlack()).isFalse();
    }
}