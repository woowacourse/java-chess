package chess.domain;

import chess.TestPiecesGenerator;
import chess.constant.ExceptionCode;
import chess.dao.InMemoryChessGameDao;
import chess.dao.InMemoryPieceDao;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.property.Color;
import chess.dto.domaintocontroller.GameStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static chess.PositionFixture.A1;
import static chess.PositionFixture.A2;
import static chess.PositionFixture.A4;
import static chess.PositionFixture.A6;
import static chess.PositionFixture.A7;
import static chess.PositionFixture.B1;
import static chess.PositionFixture.B5;
import static chess.PositionFixture.B6;
import static chess.PositionFixture.B7;
import static chess.PositionFixture.C1;
import static chess.PositionFixture.D1;
import static chess.PositionFixture.D7;
import static chess.PositionFixture.E1;
import static chess.PositionFixture.E8;
import static chess.domain.piece.property.Color.BLACK;
import static chess.domain.piece.property.Color.BLANK;
import static chess.domain.piece.property.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class ChessGameTest {

    @Test
    @DisplayName("턴이 바뀌었는지 확인한다")
    void change_turn_test() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                        new Pawn(A2, WHITE)
                )), new InMemoryChessGameDao(),
                new InMemoryPieceDao());

        chessGame.move(A2, A4);

        assertThat(chessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    @DisplayName("입력 받은 현재 위치 말 색상이 이동할 차례가 아니면, 예외를 던진다.")
    void invalid_turn_color_moving_throw_exception() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                        new Pawn(A2, WHITE),
                        new Pawn(A7, BLACK)
                )), new InMemoryChessGameDao(),
                new InMemoryPieceDao());

        assertThatThrownBy(() -> chessGame.move(A7, A6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionCode.INVALID_TURN.name());
    }

    @Test
    @DisplayName("왕이 잡혔는지 확인한다")
    void king_caught_state_check_test() {
        final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                        new King(E1, WHITE),
                        new King(E8, BLACK),
                        new Queen(B5, WHITE)
                )), new InMemoryChessGameDao(),
                new InMemoryPieceDao());

        final boolean initialKingCaught = chessGame.isKingCaught();
        chessGame.move(B5, E8);
        final boolean afterMovedKingCaught = chessGame.isKingCaught();

        assertSoftly(softly -> {
            softly.assertThat(initialKingCaught).isFalse();
            softly.assertThat(afterMovedKingCaught).isTrue();
        });
    }

    @Nested
    @DisplayName("게임의 상태를 확인한다")
    class GameStatusTest {

        @Test
        @DisplayName("두 왕이 모두 살아있는 경우 각 팀의 점수를 계산한다.")
        void status_when_game_does_not_end_test() {
            final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                            new King(E8, BLACK),    // 0
                            new Pawn(B7, BLACK),    // 0.5
                            new Pawn(B6, BLACK),    // 0.5
                            new Pawn(B5, BLACK),    // 0.5
                            new Pawn(D7, BLACK),    // 1.0

                            new King(E1, WHITE),    // 0
                            new Queen(D1, WHITE),   // 9.0
                            new Knight(B1, WHITE),  // 2.5
                            new Bishop(C1, WHITE),  // 3
                            new Rook(A1, WHITE)     // 5
                    )), new InMemoryChessGameDao(),
                    new InMemoryPieceDao());
            final Color expectedWinningTeamColor = BLANK;
            final double expectedBlackScore = 2.5;
            final double expectedWhiteScore = 19.5;

            final GameStatus actualStatus = chessGame.getStatus();

            assertSoftly(softly -> {
                softly.assertThat(actualStatus.getWinningTeamColor()).isEqualTo(expectedWinningTeamColor);
                softly.assertThat(actualStatus.getBlackScore()).isEqualTo(expectedBlackScore);
                softly.assertThat(actualStatus.getWhiteScore()).isEqualTo(expectedWhiteScore);
            });
        }

        @Test
        @DisplayName("승리팀이 있는경우 승리팀의 색을 구하고 점수는 모두 0점처리 한다 - Black 팀 승리")
        void status_when_black_win_test() {
            final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                            new King(E8, BLACK),
                            new Pawn(B7, BLACK),
                            new Pawn(B6, BLACK),
                            new Pawn(B5, BLACK),
                            new Pawn(D7, BLACK),

                            new Queen(D1, WHITE),
                            new Knight(B1, WHITE),
                            new Bishop(C1, WHITE),
                            new Rook(A1, WHITE)
                    )), new InMemoryChessGameDao(),
                    new InMemoryPieceDao());
            final Color expectedWinningTeamColor = BLACK;
            final double expectedBlackScore = 0;
            final double expectedWhiteScore = 0;

            final GameStatus actualStatus = chessGame.getStatus();

            assertSoftly(softly -> {
                softly.assertThat(actualStatus.getWinningTeamColor()).isEqualTo(expectedWinningTeamColor);
                softly.assertThat(actualStatus.getBlackScore()).isEqualTo(expectedBlackScore);
                softly.assertThat(actualStatus.getWhiteScore()).isEqualTo(expectedWhiteScore);
            });
        }

        @Test
        @DisplayName("승리팀이 있는경우 승리팀의 색을 구하고 점수는 모두 0점처리 한다 - White 팀 승리")
        void status_when_white_win_test() {
            final ChessGame chessGame = ChessGame.createWith(new TestPiecesGenerator(List.of(
                            new Pawn(B7, BLACK),
                            new Pawn(B6, BLACK),
                            new Pawn(B5, BLACK),
                            new Pawn(D7, BLACK),

                            new King(E1, WHITE),
                            new Queen(D1, WHITE),
                            new Knight(B1, WHITE),
                            new Bishop(C1, WHITE),
                            new Rook(A1, WHITE)
                    )), new InMemoryChessGameDao(),
                    new InMemoryPieceDao());
            final Color expectedWinningTeamColor = WHITE;
            final double expectedBlackScore = 0;
            final double expectedWhiteScore = 0;

            final GameStatus actualStatus = chessGame.getStatus();

            assertSoftly(softly -> {
                softly.assertThat(actualStatus.getWinningTeamColor()).isEqualTo(expectedWinningTeamColor);
                softly.assertThat(actualStatus.getBlackScore()).isEqualTo(expectedBlackScore);
                softly.assertThat(actualStatus.getWhiteScore()).isEqualTo(expectedWhiteScore);
            });
        }

    }
}
