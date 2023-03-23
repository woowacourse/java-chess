package chess.domain.state;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.controller.command.Command;
import chess.domain.ChessGame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessReadyTest {

    @Nested
    @DisplayName("체스 게임  커맨트 진행 테스트")
    class command_process_test {

        @Test
        @DisplayName("게임 시작 명령시 상태를 진행중으로 변경한다")
        void start_chess_test() {
            final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

            final ChessState state = chessReady.process(Command.of(List.of("start")));

            assertThat(state).isInstanceOf(ChessRunning.class);
        }

        @Test
        @DisplayName("이동 명령시 예외를 발생시킨다")
        void move_chess_command_throw_exception() {
            final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

            assertThatThrownBy(() -> chessReady.process(Command.of(List.of("move", "a2", "a3"))))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(ExceptionCode.GAME_NOT_INITIALIZED.name());
        }

        @Test
        @DisplayName("게임 종료 명령시 상태를 종료 변경한다")
        void end_chess_test() {
            final ChessReady chessReady = new ChessReady(ChessGame.createWith(new TestPiecesGenerator(Collections.emptyList())));

            final ChessState state = chessReady.process(Command.of(List.of("end")));

            assertThat(state).isInstanceOf(ChessEnd.class);
        }
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
