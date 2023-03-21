package chess.domain;

import static chess.util.SquareFixture.A_SEVEN;
import static chess.util.SquareFixture.A_SIX;
import static chess.util.SquareFixture.A_THREE;
import static chess.util.SquareFixture.A_TWO;
import static chess.util.SquareFixture.B_THREE;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.board.BoardFactory;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ChessGameTest {

    @Nested
    class move_메서드는 {

        @Test
        void 움직일_수_있다면_기물을_움직인다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());

            assertThatNoException().isThrownBy(() -> chessGame.move(A_TWO, A_THREE));
        }

        @Test
        void 움직일_수_없는_위치라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(A_TWO, B_THREE))
                    .withMessage("움직일 수 없는 위치입니다.");
        }

        @Test
        void 기물이_존재하지_않으면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());
            chessGame.move(A_TWO, A_THREE);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(A_TWO, A_THREE))
                    .withMessage("움직일 기물이 존재하지 않습니다.");
        }

        @Test
        void 알맞은_차례가_아니라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame(BoardFactory.create());

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(A_SEVEN, A_SIX))
                    .withMessage("WHITE의 차례입니다.");
        }
    }
}
