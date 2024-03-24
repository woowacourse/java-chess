package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A1;
import static chess.domain.fixture.CoordinateFixture.D4;
import static chess.domain.fixture.CoordinateFixture.E4;
import static chess.domain.fixture.CoordinateFixture.F4;
import static chess.domain.fixture.CoordinateFixture.G4;
import static chess.domain.fixture.CoordinateFixture.H4;
import static chess.domain.fixture.CoordinateFixture.H8;
import static chess.domain.fixture.PieceFixture.WHITE_ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import chess.domain.piece.directionmove.Rook;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new Rook(Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("룩은 가로, 세로로 제한없이 움직일 수 있다.")
    @Test
    void findMovablePath() {
        List<Coordinate> result = WHITE_ROOK.legalNextCoordinates(D4, H4);

        List<Coordinate> expected = List.of(E4, F4, G4, H4);
        assertThat(result).containsExactlyElementsOf(expected);
    }

    @DisplayName("룩이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> WHITE_ROOK.legalNextCoordinates(A1, H8))
                .isInstanceOf(IllegalStateException.class);
    }
}
