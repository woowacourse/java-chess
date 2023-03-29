package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("End 는")
class EndTest {

    @Test
    void 종료_상태로_상태를_바꾸면_예외처리() {
        // given
        final ChessState state = new End(new Turn(Color.WHITE));
        // when & then
        assertThatThrownBy(state::finish)
                .isInstanceOf(IllegalStateException.class);
    }
}
