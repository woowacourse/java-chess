package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

    @DisplayName("start 메소드는 예외를 던진다.")
    @Test
    void start_throwsException() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when & then
        Assertions.assertThatThrownBy(whiteTurn::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임 중 이므로 게임을 시작할 수 없습니다.");
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
        assertThatThrownBy(() -> whiteTurn.move(Position.from(XAxis.A, YAxis.ONE), Position.from(XAxis.A, YAxis.ONE)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("말을 이동하는 것에 실패했습니다.");
    }

    @DisplayName("move 메소드 호출 시 흑색 킹이 죽었다면 ReadyToStart 를 생성하여 반환한다.")
    @Test
    void move_returnsNewReadyToStartOnKilledKing() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        whiteTurn.move(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE));
        whiteTurn.move(Position.from(XAxis.C, YAxis.THREE), Position.from(XAxis.B, YAxis.FIVE));
        whiteTurn.move(Position.from(XAxis.B, YAxis.FIVE), Position.from(XAxis.C, YAxis.SEVEN));
        GameState actual = whiteTurn.move(Position.from(XAxis.C, YAxis.SEVEN), Position.from(XAxis.E, YAxis.EIGHT));

        // then
        assertThat(actual).isInstanceOf(ReadyToStart.class);
    }

    @DisplayName("move 메소드 호출 시 말이 움직이기만 했다면 BlackTurn 을 생성하여 반환한다.")
    @Test
    void move_returnsNewBlackTurn() {
        // given
        GameState whiteTurn = new WhiteTurn();

        // when
        GameState actual = whiteTurn.move(Position.from(XAxis.B, YAxis.ONE), Position.from(XAxis.C, YAxis.THREE));

        // then
        assertThat(actual).isInstanceOf(BlackTurn.class);
    }
}