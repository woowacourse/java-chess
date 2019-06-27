package chess.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {
    @Test
    void white팀_턴이_맞는지_확인() {
        ChessGame game = new ChessGame(new NewGameCreateStrategy(), 1);

        assertThat(game.isRightTurn("11")).isTrue();
    }

    @Test
    void black팀_턴이_맞는지_확인() {
        ChessGame game = new ChessGame(new NewGameCreateStrategy(), 1);

        assertThat(game.isRightTurn("18")).isFalse();
    }
}
