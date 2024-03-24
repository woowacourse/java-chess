package chess.domain.piece;

import static chess.domain.fixture.CoordinateFixture.B5;
import static chess.domain.fixture.CoordinateFixture.C4;
import static chess.domain.fixture.CoordinateFixture.C5;
import static chess.domain.fixture.CoordinateFixture.C6;
import static chess.domain.fixture.CoordinateFixture.D5;
import static chess.domain.fixture.PieceFixture.NORMAL_BLACK_PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Coordinate;
import chess.domain.piece.pawn.NormalBlackPawn;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalBlackPawnTest {

    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(NormalBlackPawn::new)
                .doesNotThrowAnyException();
    }

    @DisplayName("일반 검은색 폰의 이동 가능한 모든 좌표를 계산한다.")
    @Test
    void legalNextCoordinatesBlackPawn() {
        List<Coordinate> result = NORMAL_BLACK_PAWN.legalNextCoordinates(C6, C5);

        List<Coordinate> expected = List.of(C5, D5, B5);
        assertThat(result).containsAll(expected);
    }

    @DisplayName("일반 검은색 폰이 목적지로 갈 수 없는 경우, 예외를 발생한다.")
    @Test
    void noPath() {
        assertThatThrownBy(() -> NORMAL_BLACK_PAWN.legalNextCoordinates(C6, C4))
                .isInstanceOf(IllegalStateException.class);
    }
}
