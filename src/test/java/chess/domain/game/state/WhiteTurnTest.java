package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @DisplayName("start 메소드는 ReadyToStart를 반환한다.")
    @Test
    void start_returnsReadyToStart() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        GameState actual = whiteTurn.start();

        // when & then
        assertThat(actual).isInstanceOf(ReadyToStart.class);
    }

    @DisplayName("status 메소드는 ScoreResult 를 생성하여 반환한다.")
    @Test
    void status_returnNewReadyToStart() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        ScoreResult actual = whiteTurn.status();

        // then
        assertThat(actual).isInstanceOf(ScoreResult.class);
    }

    @DisplayName("getBoard 메소드는 현재 게임의 Board 를 반환한다.")
    @Test
    void getBoard_returnCurrentBoard() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        Board actual = whiteTurn.getBoard();

        // then
        assertThat(actual).isInstanceOf(Board.class);
    }

    @DisplayName("move 메소드 호출 시 말 이동이 실패했다면 예외를 던진다.")
    @Test
    void move_throwsExceptionOnFailed() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when & then
        assertThatThrownBy(() -> whiteTurn.move(Position.of(XAxis.A, YAxis.ONE), Position.of(XAxis.A, YAxis.ONE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("말을 이동하는 것에 실패했습니다.");
    }

    @DisplayName("한쪽 킹이 죽었다면 기물을 움직일 수 없다.")
    @Test
    void move_returnsNewReadyToStartOnKilledKing() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        whiteTurn.move(Position.of(XAxis.B, YAxis.ONE), Position.of(XAxis.C, YAxis.THREE));
        whiteTurn.move(Position.of(XAxis.C, YAxis.THREE), Position.of(XAxis.B, YAxis.FIVE));
        whiteTurn.move(Position.of(XAxis.B, YAxis.FIVE), Position.of(XAxis.C, YAxis.SEVEN));
        whiteTurn.move(Position.of(XAxis.C, YAxis.SEVEN), Position.of(XAxis.E, YAxis.EIGHT));

        // then
        assertThatThrownBy(() -> whiteTurn.move(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.THREE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("킹이 죽었으므로 더이상 게임을 진행할 수 없습니다.");
    }

    @DisplayName("move 메소드 호출 시 말이 움직이기만 했다면 BlackTurn 을 생성하여 반환한다.")
    @Test
    void move_returnsNewBlackTurn() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        GameState actual = whiteTurn.move(Position.of(XAxis.B, YAxis.ONE), Position.of(XAxis.C, YAxis.THREE));

        // then
        assertThat(actual).isInstanceOf(BlackTurn.class);
    }
}
