package chess.domain.state;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.domain.ChessGame;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.maker.StartingPiecesGenerator;
import chess.dto.domaintocontroller.GameStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static chess.PositionFixture.B5;
import static chess.PositionFixture.B6;
import static chess.PositionFixture.E1;
import static chess.PositionFixture.E8;
import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.BLANK;
import static chess.domain.piece.property.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessRunningTest {

    private static final List<Piece> pieces = List.of(
            new King(E1, WHITE),
            new King(E8, BLACK),
            new Queen(B5, WHITE)
    );

    @Test
    @DisplayName("게임 시작 명령시 예외를 발생시킨다")
    void start_chess_command_throw_exception() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(pieces)));

        assertThatThrownBy(() -> chessRunning.start())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(ExceptionCode.GAME_ALREADY_RUNNING.name());
    }

    @Test
    @DisplayName("이동 명령시 진행중인 상태의 체스를 반환한다")
    void move_chess_command_throw_exception() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(pieces)));

        final ChessState state = chessRunning.move(B5, B6);

        assertThat(state).isInstanceOf(ChessRunning.class);
    }

    @Test
    @DisplayName("게임 종료 명령시 상태를 종료 변경한다")
    void end_chess_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(pieces)));

        final ChessState state = chessRunning.end();

        assertThat(state).isInstanceOf(ChessEnd.class);
    }

    @Test
    @DisplayName("말들의 점수 상태를 반환한다")
    void get_score_from_status_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new StartingPiecesGenerator()));

        final GameStatus status = chessRunning.status();

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(status.getWinningTeamColor()).isEqualTo(BLANK);
            softly.assertThat(status.getBlackScore()).isEqualTo(38.0);
            softly.assertThat(status.getWhiteScore()).isEqualTo(38.0);
        });
    }

    @Test
    @DisplayName("체스말을 가져온다")
    void getting_existing_piece_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(pieces)));

        final Set<Piece> existingPieces = chessRunning.getExistingPieces();

        assertThat(existingPieces).containsExactlyInAnyOrderElementsOf(pieces);
    }

    @Test
    @DisplayName("게임 종료 여부를 확인한다")
    void check_if_is_end_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(pieces)));

        final boolean isEnd = chessRunning.isEnd();

        assertThat(isEnd).isFalse();
    }

    @Test
    @DisplayName("왕이 잡히면 게임오버 상태를 반환한다")
    void king_caught_state_check_test() {
        final ChessRunning chessRunning = new ChessRunning(ChessGame.createWith(new TestPiecesGenerator(List.of(
                new King(E1, WHITE),
                new King(E8, BLACK),
                new Queen(B5, WHITE)
        ))));

        final ChessState resultState = chessRunning.move(B5, E8);

        assertThat(resultState).isInstanceOf(ChessGameOver.class);
    }

}
