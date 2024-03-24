package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A3;
import static chess.domain.fixture.CoordinateFixture.E4;
import static chess.domain.fixture.CoordinateFixture.E5;
import static chess.domain.fixture.PieceFixture.WHITE_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import chess.domain.piece.fixedmove.King;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new King(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("킹은 가로, 세로 및 대각선으로도 1칸씩 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_KING.legalNextCoordinates(E4, E5);

        List<Coordinate> expected = List.of(E5);
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("킹이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_KING.legalNextCoordinates(A1, A3))
                .isInstanceOf(IllegalStateException.class);
    }
}
