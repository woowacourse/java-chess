package chess;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChessChessRankTest {

    @Test
    void of_RankPosition_ReturnInstance() {
        assertThat(ChessRank.of(1)).isInstanceOf(ChessRank.class);
    }

    @Test
    void of_EqualInstance_ReturnTrue() {
        ChessRank chessRank1 = ChessRank.of(1);
        ChessRank chessRank2 = ChessRank.of(1);

        assertThat(chessRank1.equals(chessRank2)).isTrue();
        assertThat(chessRank1 == chessRank2).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void validate_InvalidChessRank_ExceptionThrown(int invalidChessRank) {
        assertThatThrownBy(() -> ChessRank.of(invalidChessRank))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
