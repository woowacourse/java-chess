package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.info.Team;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Nested
    class 현재_팀{

        @Test
        void turn이_짝수라면_white팀을_반환한다() {
            //given
            Turn turn = new Turn();
            Team expected = Team.WHITE;

            //when
            Team actual = turn.findCurrentTeam();

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void turn이_홀수라면_black팀을_반환한다() {
            //given
            Turn turn = new Turn();
            Team expected = Team.BLACK;
            turn = turn.next();

            //when
            Team actual = turn.findCurrentTeam();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 상대_팀{

        @Test
        void turn이_짝수라면_black팀을_반환한다() {
            //given
            Turn turn = new Turn();
            Team expected = Team.BLACK;

            //when
            Team actual = turn.findCurrentEnemyTeam();

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void turn이_홀수라면_white팀을_반환한다() {
            //given
            Turn turn = new Turn();
            Team expected = Team.WHITE;
            turn = turn.next();

            //when
            Team actual = turn.findCurrentEnemyTeam();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}
