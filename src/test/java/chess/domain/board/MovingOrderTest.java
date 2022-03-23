package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MovingOrderTest {

    @ParameterizedTest
    @CsvSource(value = {
            "A,TWO,A,THREE,TOP",
            "A,SEVEN,A,SIX,DOWN",
            "A,TWO,B,TWO,RIGHT",
            "B,TWO,A,TWO,LEFT",
            "A,TWO,B,THREE,TOPRIGHT",
            "B,TWO,A,THREE,TOPLEFT",
            "A,SEVEN,B,SIX,DOWNRIGHT",
            "B,SEVEN,A,SIX,DOWNLEFT",
            "B,ONE,C,THREE,TTR",
            "B,ONE,D,TWO,RRT",
            "B,TWO,D,ONE,RRD",
            "B,THREE,C,ONE,DDR",
            "B,THREE,A,ONE,DDL",
            "C,TWO,A,ONE,LLD",
            "C,TWO,A,THREE,LLT",
            "B,ONE,A,THREE,TTL"
    }
    )
    @DisplayName("체스 말이 거리가 같은지 확인할 수 있다.")
    void isSameDirection(File fileA, Rank rankA, File fileB, Rank rankB, Direction direction) {
        MovingOrder movingOrder = new MovingOrder(
                new Position(fileA, rankA),
                new Position(fileB, rankB)
        );

        assertThat(movingOrder.isSameDirection(direction)).isTrue();
    }
}
