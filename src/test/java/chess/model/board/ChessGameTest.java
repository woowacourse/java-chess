package chess.model.board;

import static chess.model.board.PositionFixture.A1;
import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.ChessGame;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @Test
    @DisplayName("에러가 발생하면 현재 턴이 유지된다.")
    void whenError_thenMaintainTurn() {
        // given
        final ChessGame chessGame = new ChessGame();
        chessGame.move(A2, A3);

        // when, then
        assertAll(
                () -> assertThatThrownBy(() -> chessGame.move(A1, A2))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThat(chessGame)
                        .extracting("turn", InstanceOfAssertFactories.type(Turn.class))
                        .extracting("count", InstanceOfAssertFactories.INTEGER)
                        .isEqualTo(1)
        );
    }
}
