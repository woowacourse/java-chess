package chess.model.game;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.piece.Camp;
import chess.view.ChessBoardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    private ChessGame chessGame;

    @BeforeEach
    void beforeEach() {
        chessGame = new ChessGame();
    }

    @Test
    @DisplayName("initialChessGame()은 호출하면 게임을 진행할 수 있는 상태로 변경한다")
    void initialChessGame_whenCall_thenSuccess() {
        // when
        chessGame.initialChessGame();

        // then
        final ChessBoardResponse chessBoardResponse = chessGame.getChessBoard();

        assertThat(chessBoardResponse).isNotNull();
    }
    
    @Test
    @DisplayName("move()는 source와 target으로 동일한 Position을 건네주면 예외가 발생한다.")
    void move_givenSameSourceAndTarget_thenFail() {
        // given
        chessGame.initialChessGame();

        // when, then
        assertThatThrownBy(() -> chessGame.move(A1, A1, Camp.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 위치로 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("move() 메소드는 체스 판과 플레이어 진행 턴을 초기화하지 않았다면 예외가 발생한다.")
    void move_whenCallBeforeInitial_thenFail() {
        // when, then
        assertThatThrownBy(() -> chessGame.move(A1, A2, Camp.BLACK))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 진행할 수 없는 상태입니다.");
    }
}
