package chess.domain.game;

import static chess.domain.piece.Color.WHITE;
import static chess.util.SquareFixture.B_THREE;
import static chess.util.SquareFixture.B_TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    @Nested
    class start_메서드는 {

        @Test
        void 시작_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();

            assertThatIllegalStateException()
                    .isThrownBy(chessGame::start)
                    .withMessage("시작 상태에서는 시작할 수 없습니다.");
        }

        @Test
        void 종료_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();
            chessGame.end();

            assertThatIllegalStateException()
                    .isThrownBy(chessGame::start)
                    .withMessage("종료 상태에서는 시작할 수 없습니다.");
        }
    }

    @Nested
    class calculateScore_메서드는 {

        @Test
        void 초기_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());

            assertThatIllegalStateException()
                    .isThrownBy(() -> chessGame.calculateScore(WHITE))
                    .withMessage("초기 상태에서는 점수를 계산할 수 없습니다.");
        }

        @Test
        void 시작_상태라면_점수를_계산한다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();

            assertThat(chessGame.calculateScore(WHITE)).isEqualTo(38);
        }

        @Test
        void 종료_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();
            chessGame.end();

            assertThatIllegalStateException()
                    .isThrownBy(() -> chessGame.calculateScore(WHITE))
                    .withMessage("종료 상태에서는 점수를 계산할 수 없습니다.");
        }
    }

    @Nested
    class move_메서드는 {

        @Test
        void 초기_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());

            assertThatIllegalStateException()
                    .isThrownBy(() -> chessGame.move(B_TWO, B_THREE))
                    .withMessage("초기 상태에서는 움직일 수 없습니다.");
        }

        @Test
        void 종료_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();
            chessGame.end();

            assertThatIllegalStateException()
                    .isThrownBy(() -> chessGame.move(B_TWO, B_THREE))
                    .withMessage("종료 상태에서는 움직일 수 없습니다.");
        }
    }

    @Nested
    class end_메서드는 {

        @Test
        void 종료_상태라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();
            chessGame.end();

            assertThatIllegalStateException()
                    .isThrownBy(chessGame::end)
                    .withMessage("종료 상태에서는 종료할 수 없습니다.");
        }
    }

    @Test
    void 색의_점수를_계산한다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.create());

        assertThat(chessGame.calculateScoreOfColor(WHITE)).isEqualTo(38);
    }

    @Nested
    class isRunning_메서드는 {

        @Test
        void 초기_상태라면_true_반환한다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());

            assertThat(chessGame.isRunning()).isTrue();
        }

        @Test
        void 시작_상태라면_true_반환한다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();

            assertThat(chessGame.isRunning()).isTrue();
        }

        @Test
        void 종료_상태라면_false_반환한다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.start();
            chessGame.end();

            assertThat(chessGame.isRunning()).isFalse();
        }
    }
}
