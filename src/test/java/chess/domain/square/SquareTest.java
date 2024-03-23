package chess.domain.square;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("좌표")
class SquareTest {
    @Test
    @DisplayName("사용자 입력으로 들어온 좌표를 파일과 랭크로 변환한다.")
    void convertUserInputToFileAndRank() {
        Square square = Square.from("b3");
        assertThat(square.column()).isEqualTo(File.b);
        assertThat(square.rank()).isEqualTo(Rank.THREE);
    }
}
