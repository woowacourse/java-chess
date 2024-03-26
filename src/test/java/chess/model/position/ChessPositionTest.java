package chess.model.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChessPositionTest {

    @Test
    @DisplayName("캐싱되어 항상 동일한 객체를 반환한다.")
    void from() {
        // given
        ChessPosition chessPosition = ChessPosition.of(File.A, Rank.ONE);
        ChessPosition otherChessPosition = ChessPosition.of(File.A, Rank.ONE);

        // when
        boolean result = chessPosition.equals(otherChessPosition);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("타겟 위치와 소스 위치 간의 이동량을 계산한다.")
    void calculateMovement() {
        // given
        ChessPosition sourcePosition = ChessPosition.of(File.A, Rank.FOUR);
        ChessPosition targetPosition = ChessPosition.of(File.B, Rank.TWO);

        // when
        Movement movement = targetPosition.calculateMovement(sourcePosition);

        // then
        Difference fileDifference = Difference.from(1);
        Difference rankDifference = Difference.from(-2);
        assertThat(movement).isEqualTo(new Movement(fileDifference, rankDifference));
    }

    @Test
    @DisplayName("오프셋으로 다음 위치를 계산한다.")
    void calculateNextPosition() {
        // given
        ChessPosition chessPosition = ChessPosition.of(File.A, Rank.FOUR);
        int fileOffset = 2;
        int rankOffset = -1;

        // when
        ChessPosition nextPosition = chessPosition.calculateNextPosition(fileOffset, rankOffset);

        // then
        assertThat(nextPosition).isSameAs(ChessPosition.of(File.C, Rank.THREE));
    }

}
