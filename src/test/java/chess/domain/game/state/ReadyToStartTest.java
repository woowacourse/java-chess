package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyToStartTest {

    @DisplayName("start 메소드는 WhiteTurn 을 생성하여 반환한다.")
    @Test
    void start_returnsNewWhiteTurn() {
        // given
        GameState readyToStart = new ReadyToStart();

        // when
        GameState actual = readyToStart.start();

        // then
        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }

    @DisplayName("status 메소드는 예외를 던진다.")
    @Test
    void status_throwsException() {
        // given
        GameState readyToStart = new ReadyToStart();

        // when & then
        assertThatThrownBy(readyToStart::status)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임중이 아니므로 점수를 출력할 수 없습니다.");
    }

    @DisplayName("move 메소드는 예외를 던진다.")
    @Test
    void move_throwsException() {
        // given
        GameState readyToStart = new ReadyToStart();

        // when & then
        assertThatThrownBy(
                () -> readyToStart.move(Position.of(XAxis.A, YAxis.ONE), Position.of(XAxis.A, YAxis.TWO)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임중이 아니므로 말을 이동할 수 없습니다.");
    }

    @DisplayName("getBoard 메소드는 예외를 던진다.")
    @Test
    void getBoard_throwsException() {
        // given
        GameState readyToStart = new ReadyToStart();

        // when & then
        assertThatThrownBy(readyToStart::getBoard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임중이 아니므로 체스판이 아직 생성되지 않았습니다.");
    }
}
