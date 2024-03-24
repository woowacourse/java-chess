package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.A7;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.E5;
import static chess.domain.fixture.CoordinateFixture.F6;
import static chess.domain.fixture.CoordinateFixture.G7;
import static chess.domain.fixture.CoordinateFixture.H8;
import static chess.domain.fixture.PieceFixture.WHITE_BISHOP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Bishop(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("비숍은 대각으로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_BISHOP.legalNextCoordinates(D4, G7);

        List<Coordinate> expected = List.of(E5, F6, G7, H8);
        assertThat(result)
                .containsExactlyElementsOf(expected);
    }

    @DisplayName("비숍이 목적지로 갈 수 없는 경우, 예외가 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_BISHOP.legalNextCoordinates(A1, A7))
                .isInstanceOf(IllegalStateException.class);
    }
}
