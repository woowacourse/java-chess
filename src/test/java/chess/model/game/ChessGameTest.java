package chess.model.game;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.A2;
import static chess.helper.PositionFixture.A3;
import static chess.helper.PositionFixture.A6;
import static chess.helper.PositionFixture.B2;
import static chess.helper.PositionFixture.B3;
import static chess.helper.PositionFixture.B6;
import static chess.helper.PositionFixture.B7;
import static chess.helper.PositionFixture.C1;
import static chess.helper.PositionFixture.C8;
import static chess.helper.PositionFixture.E1;
import static chess.helper.PositionFixture.E2;
import static chess.helper.PositionFixture.E3;
import static chess.helper.PositionFixture.E6;
import static chess.helper.PositionFixture.E7;
import static chess.helper.PositionFixture.E8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.view.dto.ChessBoardResponse;
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
        assertThatThrownBy(() -> chessGame.move(A1, A1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 위치로 기물을 이동시킬 수 없습니다.");
    }

    @Test
    @DisplayName("move() 메소드는 체스 판과 플레이어 진행 턴을 초기화하지 않았다면 예외가 발생한다.")
    void move_whenCallBeforeInitial_thenFail() {
        // when, then
        assertThatThrownBy(() -> chessGame.move(A1, A2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("게임을 진행할 수 없는 상태입니다.");
    }

    @Test
    @DisplayName("isGameOnGoing()은 플레이어가 흰색 진영일 때 검은색 킹이 없다면 false를 반환한다.")
    void isGameOnGoing_whenDeadBlackKing_thenReturnTrue() {
        // given
        chessGame.initialChessGame();
        chessGame.move(B2, B3);
        chessGame.move(E7, E6);
        chessGame.move(C1, A3);
        chessGame.move(E8, E7);
        chessGame.move(A3, E7);

        // when
        final boolean actual = chessGame.isGameOnGoing();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isGameOnGoing()은 플레이어가 흰색 진영일 때 검은색 킹이 있다면 true를 반환한다.")
    void isGameOnGoing_whenAliveBlackKing_thenReturnTrue() {
        // given
        chessGame.initialChessGame();

        // when
        final boolean actual = chessGame.isGameOnGoing();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("isGameOnGoing()은 플레이어가 검은색 진영일 때 흰색 킹이 없다면 false를 반환한다.")
    void isGameOnGoing_whenDeadWhiteKing_thenReturnTrue() {
        // given
        chessGame.initialChessGame();
        chessGame.move(E2, E3);
        chessGame.move(B7, B6);
        chessGame.move(E1, E2);
        chessGame.move(C8, A6);
        chessGame.move(A2, A3);
        chessGame.move(A6, E2);

        // when
        final boolean actual = chessGame.isGameOnGoing();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("isGameOnGoing()은 플레이어가 검은색 진영일 때 흰색 킹이 있다면 true를 반환한다.")
    void isGameOnGoing_whenAliveWhiteKing_thenReturnTrue() {
        // given
        chessGame.initialChessGame();
        chessGame.move(A2, A3);

        // when
        final boolean actual = chessGame.isGameOnGoing();

        // then
        assertThat(actual).isTrue();
    }
}
