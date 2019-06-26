package chess.model;

import chess.model.gameCreator.NewGameCreateStrategy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameTest {
    @Test
    void white팀_턴이_맞는지_확인() {
        ChessGame game = new ChessGame(new NewGameCreateStrategy(), 3);
        assertThat(game.getCurrentTeam()).isEqualTo("white");
    }

    @Test
    void black팀_턴이_맞는지_확인() {
        ChessGame game = new ChessGame(new NewGameCreateStrategy(), 4);
        assertThat(game.getCurrentTeam()).isEqualTo("black");
    }
}
