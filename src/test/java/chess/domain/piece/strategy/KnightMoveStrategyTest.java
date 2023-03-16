package chess.domain.piece.strategy;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class KnightMoveStrategyTest {

    @Test
    @DisplayName("나이트 이동 성공 케이스")
    void move_success() {
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();
        assertThat(knightMoveStrategy.canMove(Square.of(File.B, Rank.ONE), Square.of(File.C, Rank.THREE)))
                .isTrue();
    }

    @Test
    @DisplayName("나이트 이동 실패 케이스")
    void move_fail() {
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();
        assertThat(knightMoveStrategy.canMove(Square.of(File.B, Rank.ONE), Square.of(File.C, Rank.FOUR)))
                .isFalse();
    }
}