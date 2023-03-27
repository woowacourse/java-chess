package chess.domain;

import chess.domain.board.*;
import chess.domain.piece.Color;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

class ChessGameTest {

    @Test
    void 기물을_움직인다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.generate(), ChessGame.FIRST_TURN);
        final Square source = new Square(File.A, Rank.TWO);
        final Square destination = new Square(File.A, Rank.THREE);

        assertThatNoException().isThrownBy(() -> chessGame.move(source, destination));
    }

    @Test
    void 기물을_움직일_수_없다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.generate(), ChessGame.FIRST_TURN);
        final Square source = new Square(File.A, Rank.TWO);
        final Square destination = new Square(File.B, Rank.THREE);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(source, destination))
                .withMessage("움직일 수 없는 위치입니다.");
    }

    @Test
    void 기물이_존재하지_않는다() {
        final ChessGame chessGame = new ChessGame(BoardFactory.generate(), ChessGame.FIRST_TURN);
        final Square source = new Square(File.A, Rank.TWO);
        final Square destination = new Square(File.A, Rank.THREE);
        chessGame.move(source, destination);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(source, destination))
                .withMessage("움직일 기물이 존재하지 않습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"WHITE:A:SEVEN:SIX", "BLACK:A:TWO:THREE"}, delimiter = ':')
    void 기물을_움직일_순서가_아니라면_움직일_수_없다(Color movableTurn, File file, Rank sourceRank, Rank destRank) {
        final ChessGame chessGame = new ChessGame(BoardFactory.generate(), movableTurn);
        final Square source = new Square(file, sourceRank);
        final Square destination = new Square(file, destRank);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> chessGame.move(source, destination))
                .withMessageContaining(String.format("현재 이동 가능한 기물은 %s색 입니다.", movableTurn));
    }
}
