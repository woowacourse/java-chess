package chess.domain.square;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("좌표")
class SquareTest {
    @Test
    @DisplayName("사용자 입력으로 들어온 좌표를 파일과 랭크로 변환한다.")
    void convertUserInputToFileAndRank() {
        Square square = Square.from("b3");
        assertThat(square.file()).isEqualTo(File.b);
        assertThat(square.rank()).isEqualTo(Rank.THREE);
    }
}
