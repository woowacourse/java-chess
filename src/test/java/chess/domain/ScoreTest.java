package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ScoreTest {

    @Test
    void plus_메서드를_호출하면_두_Score의_합한_Score를_반환한다() {
        //given
        Score score1 = new Score(2);
        Score score2 = new Score(3);

        //when
        Score actual = score1.add(score2);

        //then
        assertThat(actual).isEqualTo(new Score(5));
    }

}