package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.info.Team;
import org.junit.jupiter.api.Test;

class TurnTest {

    @Test
    void should_white팀을반환한다_when_turn이짝수라면() {
        //given
        Turn turn = new Turn();
        Team expected = Team.WHITE;

        //when
        Team actual = turn.findCurrentTeam();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_white팀을반환한다_when_turn이홀수라면() {
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