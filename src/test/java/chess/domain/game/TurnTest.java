package chess.domain.game;

import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    Turn turn;

    @BeforeEach
    void init() {
        turn = Turn.create();
    }

    @DisplayName("초기 턴은 화이트부터 시작이다.")
    @Test
    void initial_turn() {
        assertThat(turn.getTeam()).isEqualTo(Team.WHITE);
    }

    @DisplayName("턴은 번갈아가며 돌아간다.")
    @ParameterizedTest
    @CsvSource(value = {"1, BLACK", "2, WHITE", "3, BLACK", "4, WHITE"})
    void next_turn(int count, Team team) {
        Turn currentTurn = turn;
        for (int i = 0; i < count; i++) {
            currentTurn = currentTurn.next();
        }
        assertThat(currentTurn.getTeam()).isSameAs(team);
    }

}
