package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import chess.domain.piece.state.Bishop;
import chess.domain.piece.state.Rook;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmptyTest {

    private static final String EMPTY_ERROR_MESSAGE = "빈칸은 해당 동작을 수행할 수 없습니다.";

    @Test
    void 빈칸은_항상_비어있다() {
        assertThat(new Empty().isEmpty()).isTrue();
    }

    @Test
    void 빈칸의_팀을_물으면_예외가_발생한다() {
        //given
        final Empty empty = new Empty();

        //when & then
        assertThatThrownBy(empty::getTeam)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage(EMPTY_ERROR_MESSAGE);
    }

    @Test
    void 빈칸은_항상_다른팀이다() {
        //given
        final Rook rook = new Rook(Team.WHITE);
        final Bishop bishop = new Bishop(Team.BLACK);

        //when & then
        assertSoftly((softly) -> {
            softly.assertThat(new Empty().isSameTeam(rook)).isFalse();
            softly.assertThat(new Empty().isSameTeam(bishop)).isFalse();
        });
    }
}
