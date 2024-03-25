package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardCreator;
import chess.fixture.PositionFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TurnTest {

    @DisplayName("주어진 진영의 턴이 아니면 예외가 발생한다.")
    @Test
    void invalidTurnTest() {
        Board board = new Board(new BoardCreator());
        board.init();

        Turn turn = new Turn();

        assertThatThrownBy(() -> turn.check(board, PositionFixture.A7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주어진 진영의 턴이면 예외가 발생하지 않는다.")
    @Test
    void validTurnTest() {
        Board board = new Board(new BoardCreator());
        board.init();

        Turn turn = new Turn();

        assertThatCode(() -> turn.check(board, PositionFixture.A2))
                .doesNotThrowAnyException();
    }

    @DisplayName("턴을 넘긴다.")
    @Test
    void turnSwitchTest() {
        Board board = new Board(new BoardCreator());
        board.init();

        Turn turn = new Turn();

        turn.end();

        assertThatCode(() -> turn.check(board, PositionFixture.A7))
                .doesNotThrowAnyException();
    }
}
