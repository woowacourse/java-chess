package chess.domain.position;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessRankTest {

    @Test
    void from_RankPosition_ReturnInstance() {
        assertThat(ChessRank.ONE).isInstanceOf(ChessRank.class);
    }

    @Test
    void from_EqualInstance_ReturnTrue() {
        ChessRank chessRank1 = ChessRank.ONE;
        ChessRank chessRank2 = ChessRank.ONE;

        assertThat(chessRank1.equals(chessRank2)).isTrue();
        assertThat(chessRank1 == chessRank2).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void validate_InvalidChessRank_ExceptionThrown(int invalidChessRank) {
        assertThatThrownBy(() -> ChessRank.findValueOf(invalidChessRank))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void move_RankMovingUnit_ReturnMovedChessRank() {
        ChessRank sourceRank = ChessRank.ONE;

        ChessRank expected = ChessRank.TWO;
        assertThat(sourceRank.move(1)).isEqualTo(expected);
    }

    @Test
    void intervalTo_TargetChessRank_CalculateInterval() {
        ChessRank sourceRank = ChessRank.ONE;
        ChessRank targetRank = ChessRank.TWO;

        int expected = 2 - 1;
        assertThat(sourceRank.intervalTo(targetRank)).isEqualTo(expected);
    }

    @ParameterizedTest
    @NullSource
    void intervalTo_NullChessRank_ExceptionThrown(ChessRank chessRank) {
        assertThatThrownBy(() -> ChessRank.ONE.intervalTo(chessRank))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("비교할 타겟 랭크가 존재하지 않습니다.");
    }
}
