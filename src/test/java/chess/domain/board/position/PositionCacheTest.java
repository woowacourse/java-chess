package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PositionCacheTest {

    @Test
    @DisplayName("isCaching() : PositionCache에 값이 존재하는지 확인할 수 있다.")
    void test_isCaching() throws Exception {
        //given
        final List<String> positionCandidates = new ArrayList<>();

        for (char column = 'a'; column <= 'h'; column++) {
            for (int row = 1; row <= 8; row++) {
                positionCandidates.add(String.valueOf(column) + row);
            }
        }

        //when & then
        for (final String positionCandidate : positionCandidates) {
            assertFalse(PositionCache.isNotCaching(positionCandidate));
        }
    }
}
