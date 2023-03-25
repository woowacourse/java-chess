package chess.domain.state;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.domain.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static chess.PositionFixture.A2;
import static chess.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessReadyTest {

    @Test
    @DisplayName("게임 시작 명령시 상태를 진행중으로 변경한다")
    void start_chess_test() {
        final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

        final ChessState state = chessReady.start();

        assertThat(state).isInstanceOf(ChessRunning.class);
    }

    @Test
    @DisplayName("이동 명령시 예외를 발생시킨다")
    void move_chess_command_throw_exception() {
        final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

        assertThatThrownBy(() -> chessReady.move(A2, A3))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }

    @Test
    @DisplayName("게임 종료 명령시 상태를 종료 변경한다")
    void end_chess_test() {
        final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

        final ChessState state = chessReady.end();

        assertThat(state).isInstanceOf(ChessEnd.class);
    }

    @Test
    @DisplayName("상태 출력 명령시 예외를 발생시킨다")
    void status_command_throw_exception() {
        final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

        assertThatThrownBy(() -> chessReady.status())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }

    @Test
    @DisplayName("체스말을 가져오려하면 예외를 발생시킨다")
    void getting_existing_piece_throw_exception_test() {
        final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

        assertThatThrownBy(() -> chessReady.getExistingPieces())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.GAME_NOT_INITIALIZED.name());
    }

    @Test
    @DisplayName("게임 종료 여부를 확인한다")
    void check_if_is_end_test() {
        final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

        final boolean isEnd = chessReady.isEnd();

        assertThat(isEnd).isFalse();
    }
}
