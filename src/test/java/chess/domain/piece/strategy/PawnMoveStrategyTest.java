package chess.domain.piece.strategy;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnMoveStrategyTest {

    @Test
    void movableTest() {
        PawnMoveStrategy pawnMoveStrategy = new PawnMoveStrategy(PawnDirection.UPPER);
        Square current = Square.of(File.A, Rank.TWO);
        Square destination = Square.of(File.A, Rank.THREE);
        assertThat(pawnMoveStrategy.movable(current, destination)).isTrue();
    }
}