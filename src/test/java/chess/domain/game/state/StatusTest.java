package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.dto.CommandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {
    private final ChessGame chessGame = new ChessGame(new InitBoardStrategy());
    private State state;

    @BeforeEach
    void setup() {
        state = new Status(chessGame);
    }

    @Test
    @DisplayName("isStatus()로 스테이터스가 호출되었는지 확인할 수 있다.")
    void isStatus() {
        assertThat(state.isStatus())
                .isTrue();
    }
}
