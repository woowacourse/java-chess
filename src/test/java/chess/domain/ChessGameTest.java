package chess.domain;

import static chess.domain.board.File.A;
import static chess.domain.board.File.B;
import static chess.domain.board.Rank.SEVEN;
import static chess.domain.board.Rank.SIX;
import static chess.domain.board.Rank.THREE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.board.Square;
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
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(A, TWO);
            final Square destination = new Square(A, THREE);

            assertThatNoException().isThrownBy(() -> chessGame.move(source, destination));
        }

        @Test
        void 움직일_수_없는_위치라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(A, TWO);
            final Square destination = new Square(B, THREE);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(source, destination))
                    .withMessage("움직일 수 없는 위치입니다.");
        }

        @Test
        void 기물이_존재하지_않으면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(A, TWO);
            final Square destination = new Square(A, THREE);
            chessGame.move(source, destination);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(source, destination))
                    .withMessage("움직일 기물이 존재하지 않습니다.");
        }

        @Test
        void 알맞은_차례가_아니라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(A, SEVEN);
            final Square destination = new Square(A, SIX);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(source, destination))
                    .withMessage("WHITE의 차례입니다.");
        }
    }
}
