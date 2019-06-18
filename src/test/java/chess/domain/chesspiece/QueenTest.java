package chess.domain.chesspiece;

import chess.domain.ChessPoint;
import chess.domain.RelativeChessPoint;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @Test
    void checkRule_위로_1칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 1);

        Queen queen = new Queen();

        assertThat(queen.checkRule(sourcePoint, targetPoint)).isTrue();
    }

    @Test
    void checkRule_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(1, 3);

        Queen queen = new Queen();

        assertThat(queen.checkRule(sourcePoint, targetPoint)).isTrue();
    }

    @Test
    void checkRule_위로_1칸_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 3);

        Queen queen = new Queen();

        assertThat(queen.checkRule(sourcePoint, targetPoint)).isFalse();
    }

    @Test
    void calculateUnitDirection_위로_1칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(2, 1);

        Queen queen = new Queen();

        assertThat(queen.calculateUnitDirection(sourcePoint, targetPoint)).isEqualTo(RelativeChessPoint.of(1, 0));
    }

    @Test
    void calculateUnitDirection_오른쪽으로_2칸() {
        ChessPoint sourcePoint = ChessPoint.of(1, 1);
        ChessPoint targetPoint = ChessPoint.of(1, 3);

        Queen queen = new Queen();

        assertThat(queen.calculateUnitDirection(sourcePoint, targetPoint)).isEqualTo(RelativeChessPoint.of(0, 1));
    }
}