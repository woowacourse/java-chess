package chess;

import static chess.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankTest {

    @Test
    @DisplayName("역순 정렬 테스트")
    void test() {
        List<Rank> reverseValues = Rank.reverseValues();
        assertThat(reverseValues).isEqualTo(List.of(EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, ONE));
    }
}
