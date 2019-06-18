package chess.domain.chesspiece;

import chess.domain.ChessPoint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @Test
    void checkRule_위로_1칸_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 3);

        Knight knight = new Knight();

        assertThat(knight.checkRule(sourcePoint, targetPoint)).isTrue();
    }

    @Test
    void checkRule_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(1, 3);

        Knight knight = new Knight();

        assertThat(knight.checkRule(sourcePoint, targetPoint)).isFalse();
    }
}