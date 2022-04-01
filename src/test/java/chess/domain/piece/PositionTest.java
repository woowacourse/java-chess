package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @DisplayName("칸 문자열로 위치 생성 시 해당 칸에 맞는 좌표를 갖는다.")
    @Test
    void constructor() {
        Position position = new Position("a1");

        assertThat(position.getX()).isEqualTo(0);
        assertThat(position.getY()).isEqualTo(0);
    }

    @DisplayName("입력 문자열이 두 글자가 아닌 경우 예외가 발생한다.")
    @Test
    void validateLength() {

        assertThatThrownBy(() -> new Position("a11"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("좌표는 알파벳 소문자 하나와 숫자 하나여야 합니다.");
    }

    @DisplayName("입력 문자열의 첫 글자가 a 부터 h 내에 알파벳이 아닐 경우 예외가 발생한다.")
    @Test
    void validateAlphabetRange() {

        assertThatThrownBy(() -> new Position("i1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("알파벳은 a 부터 h 까지만 가능합니다.");
    }

    @DisplayName("입력 문자열의 두번째 글자가 1 부터 8 내에 숫자가 아닐 경우 예외가 발생한다.")
    @Test
    void validateNumberRange() {

        assertThatThrownBy(() -> new Position("a9"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1 부터 8 까지만 가능합니다.");
    }

    @DisplayName("현재 위치에서 주어진 방향만큼 이동할 경우 새로운 위치를 반환한다.")
    @Test
    void createNextPosition() {
        Position position = new Position("a1");

        assertThat(position.createNextPosition(Direction.NORTH))
                .isEqualTo(new Position("a2"));
    }

    @DisplayName("현재 위치에서 주어진 방향만큼 이동할 경우 새로운 위치를 반환한다.")
    @Test
    void createNextPositionExistProduct() {
        Position position = new Position("a1");

        assertThat(position.createNextPosition(Direction.NORTH, 3))
                .isEqualTo(new Position("a4"));
    }

    @DisplayName("해당 위치가 체스판 범위 내에 있는지 확인한다.")
    @Test
    void isValidPosition() {
        assertThat(Position.isValidPosition(new Position(-1, -1))).isFalse();
    }

    @DisplayName("두 위치의 직선 거리를 계산한다.")
    @Test
    void calculateStraightDistance() {
        Position position1 = new Position(0, 0);
        Position position2 = new Position(7, 7);

        assertThat(Position.calculateStraightDistance(position1, position2))
                .isEqualTo(7);
    }

    @Test
    @DisplayName("포지션이 자신의 위치를 a1 형식으로 반환한다.")
    void getPositionString() {
        Position position1 = new Position(0, 4);
        Position position2 = new Position(0, 3);
        assertThat(position1.getPositionString()).isEqualTo("a5");
        assertThat(position2.getPositionString()).isEqualTo("a4");
    }
}
