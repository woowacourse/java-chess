package chess.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    void 기물을_움직인다() {
        final ChessGame chessGame = new ChessGame();
        final Square source = new Square(File.A, Rank.TWO);
        final Square destination = new Square(File.A, Rank.THREE);

        assertThatNoException().isThrownBy(() -> chessGame.move(source, destination));
    }

    @Test
    void 기물을_움직일_수_없다() {
        final ChessGame chessGame = new ChessGame();
        final Square source = new Square(File.A, Rank.TWO);
        final Square destination = new Square(File.B, Rank.THREE);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(source, destination))
                .withMessage("움직일 수 없는 위치입니다.");
    }

    @Test
    void 기물이_존재하지_않는다() {
        final ChessGame chessGame = new ChessGame();
        final Square source = new Square(File.A, Rank.TWO);
        final Square destination = new Square(File.A, Rank.THREE);
        chessGame.move(source, destination);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(source, destination))
                .withMessage("움직일 기물이 존재하지 않습니다.");
    }
}
