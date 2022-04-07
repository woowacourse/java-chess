package chess.domain.game.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.game.score.ScoreResult;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
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

    @DisplayName("start 메소드는 ReadyToStart를 반환한다.")
    @Test
    void start_returnsReadyToStart() {
        // given & when
        GameState actual = blackTurn.start();

        // when & then
        assertThat(actual).isInstanceOf(ReadyToStart.class);
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
                () -> blackTurn.move(Position.of(XAxis.A, YAxis.EIGHT), Position.of(XAxis.A, YAxis.SEVEN)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("말을 이동하는 것에 실패했습니다.");
    }

    @DisplayName("한쪽 킹이 죽었다면 기물을 움직일 수 없다.")
    @Test
    void move_throwsExceptionWhenKingIsKilled() {
        // when
        blackTurn.move(Position.of(XAxis.B, YAxis.EIGHT), Position.of(XAxis.C, YAxis.SIX));
        blackTurn.move(Position.of(XAxis.C, YAxis.SIX), Position.of(XAxis.B, YAxis.FOUR));
        blackTurn.move(Position.of(XAxis.B, YAxis.FOUR), Position.of(XAxis.C, YAxis.TWO));
        blackTurn.move(Position.of(XAxis.C, YAxis.TWO), Position.of(XAxis.E, YAxis.ONE));

        // then
        assertThatThrownBy(() -> blackTurn.move(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.SIX)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("킹이 죽었으므로 더이상 게임을 진행할 수 없습니다.");
    }

    @DisplayName("move 메소드 호출 시 말이 움직이기만 했다면 WhiteTurn 을 생성하여 반환한다.")
    @Test
    void move_returnsNewWhiteTurn() {
        // when
        GameState actual = blackTurn.move(Position.of(XAxis.B, YAxis.EIGHT), Position.of(XAxis.C, YAxis.SIX));

        // then
        assertThat(actual).isInstanceOf(WhiteTurn.class);
    }
}
