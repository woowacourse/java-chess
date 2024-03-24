package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.B6;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.E6;
import static chess.domain.fixture.PieceFixture.WHITE_QUEEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Queen(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("퀸은 대각과 가로, 세로로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_QUEEN.legalNextCoordinates(D4, A7);

        List<Coordinate> expected = List.of(C5, B6, A7);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("퀸이 목적지로 갈 수 없는 경우, 예외가 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_QUEEN.legalNextCoordinates(D4, E6))
                .isInstanceOf(IllegalStateException.class);
    }
}
