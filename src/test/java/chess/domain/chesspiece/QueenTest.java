package chess.domain.chesspiece;

import chess.domain.chesspoint.ChessPoint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    void checkRule_위로_1칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 1);

        Queen queen = Queen.getInstance();

        assertThat(queen.checkRule(sourcePoint, targetPoint, true)).isTrue();
    }

    @Test
    void checkRule_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(1, 3);

        Queen queen = Queen.getInstance();

        assertThat(queen.checkRule(sourcePoint, targetPoint, true)).isTrue();
    }

    @Test
    void checkRule_위로_1칸_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 3);

        Queen queen = Queen.getInstance();

        assertThat(queen.checkRule(sourcePoint, targetPoint, true)).isFalse();
    }
}