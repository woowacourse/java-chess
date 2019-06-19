package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {
    @Test
    void checkRule_위로_1칸_오른쪽으로_1칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 2);

        Bishop bishop = Bishop.getInstance();

        assertThat(bishop.checkRule(sourcePoint, targetPoint, true)).isTrue();
    }

    @Test
    void checkRule_위로_2칸_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(3, 3);

        Bishop bishop = Bishop.getInstance();

        assertThat(bishop.checkRule(sourcePoint, targetPoint, true)).isTrue();
    }

    @Test
    void checkRule_오른쪽으로_1칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(1, 2);

        Bishop bishop = Bishop.getInstance();

        assertThat(bishop.checkRule(sourcePoint, targetPoint, true)).isFalse();
    }
}