package chess.domain.game;

import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.A3;
import static chess.domain.PositionFixture.A5;
import static chess.domain.PositionFixture.D4;
import static chess.domain.PositionFixture.D5;
import static chess.domain.PositionFixture.D7;
import static chess.domain.PositionFixture.E1;
import static chess.domain.PositionFixture.E2;
import static chess.domain.PositionFixture.E3;
import static chess.domain.PositionFixture.E4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.game.exception.ChessGameException;
import chess.domain.game.state.EndState;
import chess.domain.piece.Color;
import java.util.List;
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

    @Test
    void 진행중에_킹이_죽으면_게임이_종료된다() {
        //given
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(E2, E4);
        chessGame.move(D7, D5);
        chessGame.move(E1, E2);
        chessGame.move(D5, D4);
        chessGame.move(E2, E3);
        chessGame.move(D4, E3);

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(E3, D4))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 종료되었습니다.");
    }

    @Test
    void 움직일_수_없는_장소로_움직이면_예외가_발생한다() {
        //given
        ChessGame chessGame = new ChessGame();
        chessGame.start();

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(A2, A5))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("잘못된 기물 움직임 요청입니다.");
    }

    @Test
    void 움직임을_바탕으로_보드를_생성할_수_있다() {
        //given
        List<List<Position>> positions = List.of(
                List.of(E2, E4),
                List.of(D7, D5),
                List.of(E1, E2),
                List.of(D5, D4),
                List.of(E2, E3),
                List.of(D4, E3)
        );
        ChessGame chessGame = new ChessGame(positions, EndState.getInstance());

        //when
        //then
        assertThatThrownBy(() -> chessGame.move(E3, D4))
                .isInstanceOf(ChessGameException.class)
                .hasMessage("게임이 종료되었습니다.");
    }
}
