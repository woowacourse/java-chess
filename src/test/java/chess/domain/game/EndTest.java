package chess.domain.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EndTest {

    private ChessGame chessGame;
    private End end;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(null);
        end = new End(chessGame);
    }

    @DisplayName("게임이 종료된 상태에서 move명령시 예외 발생")
    @Test
    void move() {
        assertThatThrownBy(() -> end.move(null, null))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임이 종료된 상태에서 start명령시 예외 발생")
    @Test
    void start() {
        assertThatThrownBy(() -> end.start())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임이 종료된 상태에서 end명령시 예외 발생")
    @Test
    void end() {
        assertThatThrownBy(() -> end.end())
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @DisplayName("게임이 종료된 상태인지 확인")
    @Test
    void isFinished() {
        boolean actual = end.isFinished();
        assertThat(actual).isTrue();
    }

}