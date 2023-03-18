package chess.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.board.File;
import chess.domain.board.Rank;
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
            final Square source = new Square(File.A, Rank.TWO);
            final Square destination = new Square(File.A, Rank.THREE);

            assertThatNoException().isThrownBy(() -> chessGame.move(source, destination));
        }

        @Test
        void 움직일_수_없는_위치라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(File.A, Rank.TWO);
            final Square destination = new Square(File.B, Rank.THREE);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(source, destination))
                    .withMessage("움직일 수 없는 위치입니다.");
        }

        @Test
        void 기물이_존재하지_않으면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(File.A, Rank.TWO);
            final Square destination = new Square(File.A, Rank.THREE);
            chessGame.move(source, destination);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(source, destination))
                    .withMessage("움직일 기물이 존재하지 않습니다.");
        }

        @Test
        void 알맞은_차례가_아니라면_예외를_던진다() {
            final ChessGame chessGame = new ChessGame();
            final Square source = new Square(File.A, Rank.SEVEN);
            final Square destination = new Square(File.A, Rank.SIX);

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> chessGame.move(source, destination))
                    .withMessage("WHITE의 차례입니다.");
        }
    }
}
