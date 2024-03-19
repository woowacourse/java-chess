package chess.domain.position;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("위치")
public class PositionTest {

    @DisplayName("위치를 생성한다.")
    @Test
    void createPosition() {
        // when & then
        assertThatCode(() -> Position.of(File.a, Rank.THREE)).doesNotThrowAnyException();
    }
}
