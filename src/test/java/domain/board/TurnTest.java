package domain.board;

import domain.square.Camp;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TurnTest {

    @Test
    @DisplayName("턴은 기본 상태를 가진다")
    void getTurn() {
        Turn turn = new Turn(Camp.WHITE);

        assertThat(turn.getTurn()).isEqualTo(Camp.WHITE);
    }

    @Test
    @DisplayName("첫 턴이 WHITE라면, 다음 턴은 BLACK이다")
    void invertTestWhenWhiteFirst() {
        Turn turn = new Turn(Camp.WHITE);

        turn.invert();

        assertThat(turn.getTurn()).isEqualTo(Camp.BLACK);
    }

    @Test
    @DisplayName("두번째 턴이 BLACK이라면, 다음 턴은 WHITE이다")
    void invertTestWhenWhiteSecond() {
        Turn turn = new Turn(Camp.WHITE);

        turn.invert();
        turn.invert();

        assertThat(turn.getTurn()).isEqualTo(Camp.WHITE);
    }
}
