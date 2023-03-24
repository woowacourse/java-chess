package chess.domain.game;

import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.game.exception.ChessGameException;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ChessGameTest {

    @Test
    void 시작을_하지_않은_상태로_움직이면_예외가_발생한다() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(A2, A3))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 시작을_하지_않은_상태로_보드를_가져오면_예외가_발생한다() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        //then
        assertThatThrownBy(chessGame::getPieces)
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 시작을_하지_않은_상태로_상태를_출력하면_예외가_발생한다() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        //then
        assertThatThrownBy(chessGame::getStatus)
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 시작되지 않았습니다.");
    }

    @Test
    void 시작_후에_움직이면_예외가_발생하지_않는다() {
        //given
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        //when
        //then
        assertDoesNotThrow(() -> chessGame.move(A2, A3));
    }

    @Test
    void 시작후에_보드_상태를_가져오면_예외가_발생하지_않는다() {
        //given
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        //when
        //then
        assertDoesNotThrow(chessGame::getPieces);
    }

    @Test
    void 시작후에_보드_점수를_가져오면_점수를_가져올_수_있다() {
//given
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        //when
        //then
        assertThat(chessGame.getStatus())
                .containsEntry(Color.BLACK, 38d)
                .containsEntry(Color.WHITE, 38d);
    }
}
