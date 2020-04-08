package chess.model.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.board.BoardSquare;
import org.junit.jupiter.api.Test;

class MoveSquareTest {

    @Test
    void isJumpRank() {
        assertThat(new MoveSquare("a1", "a2").isJumpRank()).isFalse();
        assertThat(new MoveSquare("a1", "a3").isJumpRank()).isTrue();
    }

    @Test
    void getBetweenWhenJumpRank() {
        assertThatThrownBy(() -> new MoveSquare("a1", "a2").getBetweenWhenJumpRank())
            .isInstanceOf(IllegalArgumentException.class);
        assertThat(new MoveSquare("a1", "a3").getBetweenWhenJumpRank())
            .isEqualTo(BoardSquare.of("a2"));
    }
}