package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReadyTest {

    private ChessGame chessGame;
    private Ready ready;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        ready = new Ready(chessGame);
    }

    @DisplayName("Ready상태에서 move명령시 예외 발생")
    @Test
    void move() {
        assertThatThrownBy(() -> ready.move(null, null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("Ready상태에서 end명령시 예외 발생")
    @Test
    void end() {
        assertThatThrownBy(() -> ready.end())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임이 종료상태인지 확인")
    @Test
    void isFinished() {
        boolean actual = ready.isFinished();
        assertThat(actual).isFalse();
    }

}