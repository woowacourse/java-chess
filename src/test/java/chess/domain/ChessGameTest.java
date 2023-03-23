package chess.domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static chess.domain.Color.BLACK;
import static chess.domain.File.A;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    @Test
    void 턴이_바뀌었는지_확인한다() {
        final ChessGame chessGame = ChessGame.createWithSetBoard();

        final ChessGame changedChessGame = chessGame.move(new Position(A, TWO), new Position(A, FOUR));

        assertThat(changedChessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    void 입력_받은_현재_위치_말_색상이_이동할_차례가_아니면_예외를_던진다() {
        final ChessGame chessGame = ChessGame.createWithSetBoard();

        assertThatThrownBy(() -> chessGame.move(new Position(A, SEVEN), new Position(A, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 색의 말을 이동시킬 순서가 아닙니다.");
    }

    @Test
    void 빈_체스판을_갖는_체스게임을_생성하였을_때_체스게임이_진행중인지_확인한다() {
        final ChessGame chessGame = ChessGame.createWithEmptyBoard();

        final boolean actual = chessGame.hasSetBoard();

        assertThat(actual).isFalse();
    }

    @Test
    void 세팅된_체스판을_갖는_체스게임을_생성하였을_때_체스게임이_진행중인지_확인한다() {
        final ChessGame chessGame = ChessGame.createWithSetBoard();

        final boolean actual = chessGame.hasSetBoard();

        assertThat(actual).isTrue();
    }
}
