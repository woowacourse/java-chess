package chess.domain.strategy.knight;

import chess.domain.Color;
import chess.domain.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightStrategyTest {

    private KnightStrategy knightStrategy;

    @BeforeEach
    void setUp() {
        knightStrategy = new KnightStrategy();
    }

    @Test
    @DisplayName("rank 차이가 1, file 차이가 2가 아닐 때 움직일 수 없다.")
    void cantMoveRankIs1AndFileIs2() {
        // when, then
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> knightStrategy.validateDirection(
                        Position.from(0, 'c'),
                        Position.from(3, 'a'),
                        Color.WHITE,
                        false
                )
        );
    }

    @Test
    @DisplayName("rank 차이가 1, file 차이가 2이면 움직일 수 있다.")
    void canMoveRankIs1AndFileIs2() {
        // given
        // when, then
        Assertions.assertDoesNotThrow(
                () -> knightStrategy.validateDirection(
                        Position.from(0, 'b'),
                        Position.from(1, 'd'),
                        Color.WHITE,
                        false
                )
        );
    }

    @Test
    @DisplayName("rank 차이가 2이고, file 차이가 1이 아닐 때 움직일 수 없다.")
    void cantMoveRankIs2AndFileIs1() {
        // when, then
        Assertions.assertThrows(
                IllegalArgumentException.class, () -> knightStrategy.validateDirection(
                        Position.from(0, 'c'),
                        Position.from(3, 'a'),
                        Color.WHITE,
                        false
                )
        );
    }

    @Test
    @DisplayName("rank 차이가 2 이고, file 차이가 1일 때 움직일 수 있다.")
    void canMoveRankIs2AndFileIs1() {
        // given
        // when, then
        Assertions.assertDoesNotThrow(
                () -> knightStrategy.validateDirection(
                        Position.from(0, 'b'),
                        Position.from(2, 'a'),
                        Color.WHITE,
                        false
                )
        );
    }

}
