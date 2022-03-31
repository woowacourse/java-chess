package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionsTest {
    @ParameterizedTest(name = "출발지 : {0}, 도착지 : {1}, 기대값 : {2}")
    @CsvSource(value = {"E5,E7,90.0", "E5,G7,45.0", "E5,G5,0.0", "E5,G3,-45.0",
            "E5,E3,-90.0", "E5,C3,-135.0", "E5,C5,180.0"})
    @DisplayName("두 지점 간 각도 계산 테스트")
    void calculateAngleOfTwoPositions(String from, String to, double expected) {
        final double actual = Directions.calculateAngleOfTwoPositions(Position.from(from), Position.from(to));
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2,A3,true", "A2,A4,true", "B2,A3,true", "B2,C3,true",
            "A2,B2,false", "A2,A1,false", "A2,A1,false", "B2,A1,false", "B2,C1,false"})
    @DisplayName("폰은 앞으로 한 칸 전진하며, 첫 수 에만 두 칸 전진 가능하다. "
            + "적 기물이 있을 시 대각 한 칸 전진하며 잡을 수 있다."
            + "좌우 또는 뒤로는 이동 불가하다.")
    void isMovableDirection(String from, String to, boolean expected) {
        // given
        final Position fromPosition = Position.from(from);
        final Position toPosition = Position.from(to);

        // when
        final boolean isMovable = Directions.isMovableDirection(new Pawn(Color.WHITE), fromPosition, toPosition);

        // then
        assertThat(isMovable).isEqualTo(expected);
    }
}
