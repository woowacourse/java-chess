package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("좌표")
class PositionTest {
    @Test
    @DisplayName("사용자 입력으로 들어온 좌표를 파일과 랭크로 변환한다.")
    void convertUserInputToFileAndRank() {
        Position position = Position.from("b3");
        assertThat(position.file()).isEqualTo(File.b);
        assertThat(position.rank()).isEqualTo(Rank.THREE);
    }
}
