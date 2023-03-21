package chess.domain.state;

import chess.TestPiecesGenerator;
import chess.controller.command.Command;
import chess.domain.ChessGame;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.domain.state.ChessRunning.CHESS_ALREADY_RUNNING_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ChessRunningTest {

    private static final Pawn pawn = new Pawn(Position.of(File.A, Rank.TWO), Color.WHITE);

    @Nested
    @DisplayName("체스 게임  커맨트 진행 테스트")
    class command_process_test {

        @Test
        @DisplayName("게임 시작 명령시 예외를 발생시킨다")
        void start_chess_command_throw_exception() {
            final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

            assertThatThrownBy(() -> chessRunning.process(Command.of((List.of("start")))))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage(CHESS_ALREADY_RUNNING_MESSAGE);
        }

        @Test
        @DisplayName("이동 명령시 진행중인 상태의 체스를 반환한다")
        void move_chess_command_throw_exception() {
            final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

            final ChessState state = chessRunning.process(Command.of(List.of("move", "a2", "a4")));

            assertThat(state).isInstanceOf(ChessRunning.class);
        }

        @Test
        @DisplayName("게임 종료 명령시 상태를 종료 변경한다")
        void end_chess_test() {
            final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

            final ChessState state = chessRunning.process(Command.of((List.of("end"))));

            assertThat(state).isInstanceOf(ChessEnd.class);
        }
    }

    @Test
    @DisplayName("체스말을 가져온다")
    void getting_existing_piece_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

        final List<Piece> existingPieces = chessRunning.getExistingPieces();
        final Piece piece = existingPieces.get(0);

        assertSoftly(softly -> {
            softly.assertThat(piece).isInstanceOf(Pawn.class);
            softly.assertThat(piece.getPosition()).isEqualTo(Position.of(File.A, Rank.TWO));
            softly.assertThat(piece.getColor()).isEqualTo(Color.WHITE);
        });

    }

    @Test
    @DisplayName("게임 종료 여부를 확인한다")
    void check_if_is_end_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(List.of(pawn))));

        final boolean isEnd = chessRunning.isEnd();

        assertThat(isEnd).isFalse();
    }

}
