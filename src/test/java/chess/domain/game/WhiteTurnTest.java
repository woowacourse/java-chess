package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhiteTurnTest {

    private ChessGame chessGame;
    private WhiteTurn whiteTurn;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        whiteTurn = new WhiteTurn(chessGame);
    }

    @DisplayName("Ready 상태에서 start 명령시 예외발생")
    @Test
    void start() {
        assertThatThrownBy(() -> whiteTurn.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임이 종료 되었는지 확인")
    @Test
    void isFinished() {
        boolean actual = whiteTurn.isFinished();
        assertThat(actual).isFalse();
    }

}