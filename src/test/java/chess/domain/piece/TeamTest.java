package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {

    @ParameterizedTest
    @DisplayName("Rank 값을 받아 팀 컬러를 반환한다.")
    @CsvSource(value = {"ONE, WHITE", "TWO, WHITE", "SEVEN, BLACK", "EIGHT, BLACK"})
    void findByRank(final Rank rank, final Team expected) {
        //when
        Team actual = Team.findByRank(rank);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("1,2,7,8 이 아닌 Rank 값으로 팀 컬러를 찾으려 하면 예외를 발생시킨다.")
    void findByRankException() {
        //given
        Rank rank = Rank.FIVE;
        //when, then
        assertThatThrownBy(() -> Team.findByRank(rank))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("팀 컬러를 식별할 수 있는 Rank는 1,2,7,8 입니다.");
    }
}
