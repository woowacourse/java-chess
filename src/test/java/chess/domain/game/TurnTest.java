package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class TurnTest {

    @ParameterizedTest
    @CsvSource(value = {"WHITE:false", "BLACK:true"}, delimiter = ':')
    void 현재_차례가_아닌지_반환한다(Color color, boolean expect) {
        Turn turn = new Turn(Color.WHITE);

        assertThat(turn.isNotCurrentTurn(color)).isEqualTo(expect);
    }

    @Test
    void 차례를_변경한다() {
        Turn turn = new Turn(Color.WHITE);

        turn.next();

        assertThat(turn.getColor()).isEqualTo(Color.BLACK);
    }
}
