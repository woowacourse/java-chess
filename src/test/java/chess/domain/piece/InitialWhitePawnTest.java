package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.A2;
import static chess.domain.fixture.CoordinateFixture.A5;
import static chess.domain.fixture.CoordinateFixture.B3;
import static chess.domain.fixture.CoordinateFixture.C2;
import static chess.domain.fixture.CoordinateFixture.C3;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.D3;
import static chess.domain.fixture.PieceFixture.INITIAL_WHITE_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialWhitePawnTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(InitialWhitePawn::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("초기 흰색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesBlackPawn() {
        List<Coordinate> result = INITIAL_WHITE_PAWN.legalNextCoordinates(C2, C4);

        List<Coordinate> expected = List.of(C3, C4, D3, B3);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("초기 흰색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> INITIAL_WHITE_PAWN.legalNextCoordinates(A2, A5))
                .isInstanceOf(IllegalStateException.class);
    }

}