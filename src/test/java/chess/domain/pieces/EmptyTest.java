package chess.domain.pieces;

import chess.domain.board.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {

    @Test
    @DisplayName("빈공간은 움직이면 에러를 발생시킨다.")
    void throws_exceptions_when_place_move() {
        // given
        Empty empty = new Empty(Team.EMPTY);

        // when & then
        Assertions.assertThatThrownBy(
            () -> empty.canMove(Position.from("c3"), Position.from("c4"), true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("빈 객체에게 경로를 요청하면 에러를 발생시킨다.")
    void throws_exceptions_when_empty_generate_route() {
        // given
        Empty empty = new Empty(Team.EMPTY);

        // when & then
        Assertions.assertThatThrownBy(
            () -> empty.generateRoute(Position.from("c3"), Position.from("c4"))
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
