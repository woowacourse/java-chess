package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackTurnTest {
    private GameState blackTurn;

    @BeforeEach
    void setUp() {
        Board initializedBoard = Board.createInitializedBoard();
        blackTurn = new BlackTurn(initializedBoard);
    }

    @DisplayName("start 메소드는 예외를 던진다.")
    @Test
    void start_throwsException() {
        // when & then
        Assertions.assertThatThrownBy(blackTurn::start)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 게임 중 이므로 게임을 시작할 수 없습니다.");
    }

    @DisplayName("status 메소드는 ScoreResult 를 생성하여 반환한다.")
    @Test
    void status_returnNewReadyToStart() {
        // when
        ScoreResult actual = blackTurn.status();

        // then
        assertThat(actual).isInstanceOf(ScoreResult.class);
    }

    @DisplayName("getBoard 메소드는 현재 게임의 Board 를 반환한다.")
    @Test
    void getBoard_returnCurrentBoard() {
        // when
        Board actual = blackTurn.getBoard();

        // then
        assertThat(actual).isInstanceOf(Board.class);
    }

    @DisplayName("move 메소드 호출 시 말 이동이 실패했다면 예외를 던진다.")
    @Test
    void move_throwsExceptionOnFailed() {
        // when & then
        assertThatThrownBy(
                () -> blackTurn.move(Position.from(XAxis.A, YAxis.EIGHT), Position.from(XAxis.A, YAxis.SEVEN)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("말을 이동하는 것에 실패했습니다.");
    }

    @DisplayName("move 메소드 호출 시 백색 킹이 죽었다면 ReadyToStart 를 생성하여 반환한다.")
    @Test
    void move_returnsNewReadyToStartOnKilledKing() {
        // when
        blackTurn.move(Position.from(XAxis.B, YAxis.EIGHT), Position.from(XAxis.C, YAxis.SIX));
        blackTurn.move(Position.from(XAxis.C, YAxis.SIX), Position.from(XAxis.B, YAxis.FOUR));
        blackTurn.move(Position.from(XAxis.B, YAxis.FOUR), Position.from(XAxis.C, YAxis.TWO));
        GameState actual = blackTurn.move(Position.from(XAxis.C, YAxis.TWO), Position.from(XAxis.E, YAxis.ONE));

        // then
        assertThat(actual).isInstanceOf(ReadyToStart.class);
    }

    @DisplayName("move 메소드 호출 시 말이 움직이기만 했다면 WhiteTurn 을 생성하여 반환한다.")
    @Test
    void move_returnsNewWhiteTurn() {
        // when
        GameState actual = blackTurn.move(Position.from(XAxis.B, YAxis.EIGHT), Position.from(XAxis.C, YAxis.SIX));

        // then
        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }
}