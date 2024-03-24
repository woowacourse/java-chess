package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A3;
import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.B3;
import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.D3;
import static chess.domain.fixture.PieceFixture.NORMAL_WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalWhitePawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(NormalWhitePawn::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("일반 흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesWhitePawn() {
        List<Coordinate> result = NORMAL_WHITE_PAWN.legalNextCoordinates(C2, C3);

        List<Coordinate> expected = List.of(C3, B3, D3);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("일반 흰색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> NORMAL_WHITE_PAWN.legalNextCoordinates(A3, A5))
                .isInstanceOf(IllegalStateException.class);
    }
}
