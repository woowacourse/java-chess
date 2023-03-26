package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Turn;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Run 은")
class RunTest {

    @Test
    void 종료_상태로_상태를_바꿀_수_있다() {
        // given
        ChessState state = new Run(new Turn(Color.WHITE));
        // when & then
        assertThat(state.finish()).isInstanceOf(End.class);
    }
}
