package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;

class BoardCacheTest {

    @Test
    @DisplayName("보드 생성 확인")
    void checkCreateBoard() {
        assertThat(BoardCache.create().getClass()).isEqualTo(LinkedHashMap.class);
    }
}
