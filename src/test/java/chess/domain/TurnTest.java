package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Turn;
import chess.domain.piece.Side;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TurnTest {

    @Test
    void 턴을_바꿀_수_있다() {
        final Turn turn = Turn.WHITE;

        assertThat(turn.change()).isEqualTo(Turn.BLACK);
    }

    @Test
    void 유효한_턴인지_판단할_수_있다() {
        final Turn turn = Turn.WHITE;

        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(turn.isTurnValid(Side.WHITE)).isTrue();
        softAssertions.assertThat(turn.isTurnValid(Side.BLACK)).isFalse();

        softAssertions.assertAll();
    }

    @Test
    void 중립은_턴을_가질_수_없다() {
        final Turn turn = Turn.WHITE;

        assertThatThrownBy(() -> turn.isTurnValid(Side.NEUTRALITY))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("기물이 있는 위치를 선택해주세요.");
    }
}
