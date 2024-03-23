package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.F2;
import static chess.domain.fixture.CoordinateFixture.F3;
import static chess.domain.fixture.PieceFixture.WHITE_KNIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Knight(Team.WHITE))
                .doesNotThrowAnyException();

    }

    @DisplayName("나이트는 사방중 한 방향으로 한 칸, 그리고 그 방향의 양 대각선 방향 중 한 방향으로 움직이는 이동 패턴을 가지고 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_KNIGHT.findMovablePath(D4, F3);

        List<Coordinate> expected = List.of(F3);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("나이트가 목적지로 갈 수 없는 경우, 빈 컬렉션을 반환한다.")
    @Test
    void noPath() {
        List<Coordinate> result = WHITE_KNIGHT.findMovablePath(D4, F2);

        assertThat(result).isEmpty();
    }
}
