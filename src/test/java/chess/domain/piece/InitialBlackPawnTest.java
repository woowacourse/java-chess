package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.B6;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.C6;
import static chess.domain.fixture.CoordinateFixture.C7;
import static chess.domain.fixture.CoordinateFixture.D6;
import static chess.domain.fixture.PieceFixture.INITIAL_BLACK_PAWN;
import static chess.domain.fixture.PieceFixture.NORMAL_BLACK_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InitialBlackPawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(InitialBlackPawn::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("초기 검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesBlackPawn() {
        List<Coordinate> result = INITIAL_BLACK_PAWN.legalNextCoordinates(C7, C5);

        List<Coordinate> expected = List.of(C6, C5, B6, D6);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("초기 검은색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> NORMAL_BLACK_PAWN.legalNextCoordinates(C7, C4))
                .isInstanceOf(IllegalStateException.class);
    }

}