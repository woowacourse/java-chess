package chess.domain.state;

import chess.domain.BoardFixture;
import chess.domain.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EndTest {
    @DisplayName("엔드 상태에서 status 실행시 Map으로 된 전수를 가져오는지 테스트")
    @Test
    void status() {
        End end = new End(BoardFixture.setup());
        assertThat(end.status()).isInstanceOf(Score.class);
    }
}
