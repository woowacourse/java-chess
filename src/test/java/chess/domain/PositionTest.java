package chess.domain;

import static chess.domain.PositionFixture.A1;
import static chess.domain.PositionFixture.A2;
import static chess.domain.PositionFixture.B1;
import static chess.domain.PositionFixture.B2;
import static chess.domain.PositionFixture.B3;
import static chess.domain.PositionFixture.B4;
import static chess.domain.PositionFixture.C2;
import static chess.domain.PositionFixture.C3;
import static chess.domain.PositionFixture.D2;
import static chess.domain.PositionFixture.D4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    @DisplayName("Position 객체 정상 생성 테스트")
    void createPosition() {
        // given
        Position position1 = Position.from("b1");
        Position position2 = Position.from("B1");

        // when & then
        assertThat(position1).isEqualTo(position2);
    }

    @Test
    @DisplayName("범위 밖 Position 생성 시도 시, UOE 발생")
    void creatingPositionWithOutOfRangeShouldFail() {
        assertThatThrownBy(() -> Position.from("A10"))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("유효하지 않은 범위입니다.");
    }

    @Test
    @DisplayName("Position 거리 계산 테스트")
    void calculatePositionDistance() {
        // given
        Position position1 = Position.from("b1");
        Position position2 = Position.from("c3");

        // when
        List<Integer> distance = position1.calculateDistance(position2);

        // then
        assertThat(distance).containsExactly(1, 2);
    }

    @Test
    @DisplayName("세로로 나열된 중간좌표 계산")
    void getPositionBetweenVertical() {
        // given & when
        List<Position> distance = B1.getPositionBetween(B4);

        // then
        assertThat(distance).containsExactly(B2, B3);
    }

    @Test
    @DisplayName("가로로 나열된 중간좌표 계산")
    void getPositionBetweenHorizontal() {
        // given & when
        List<Position> distance = A2.getPositionBetween(D2);

        // then
        assertThat(distance).containsExactly(B2, C2);
    }

    @Test
    @DisplayName("대각선 중간 좌표 계산")
    void getPositionBetweenDiagonal() {
        // given & when
        List<Position> distance = A1.getPositionBetween(D4);

        // then
        assertThat(distance).containsExactly(B2, C3);
    }
}
