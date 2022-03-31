package chess.domain.board;

import static chess.domain.PositionFixture.B3;
import static chess.domain.PositionFixture.B4;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Color;
import chess.domain.piece.Pawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionsTest {
    @ParameterizedTest(name = "출발지 : {0}, 도착지 : {1}, 기대값 : {2}")
    @CsvSource(value = {
            "E5,E7,90.0", "E5,G7,45.0", "E5,G5,0.0", "E5,C3,-135.0",
            "E5,E3,-90.0", "E5,C3,-135.0", "E5,B5,180.0", "E5,C3,-135.0"})
    @DisplayName("두 지점 간 각도 계산 테스트")
    void calculateAngleOfTwoPositions(String from, String to, double expected) {
        final double actual = Directions.calculateAngleOfTwoPositions(Position.from(from), Position.from(to));
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"A2,A4,true"})
    @DisplayName("폰은 기본적으로 앞으로만 전진 가능하다")
    void isMovableDirection(String from, String to, boolean expected) {
        assertThat(Directions.isMovableDirection(new Pawn(Color.WHITE), B3, B4)).isEqualTo(expected);
    }
}
