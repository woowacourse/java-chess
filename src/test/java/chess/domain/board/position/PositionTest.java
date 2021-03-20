package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @Test
    @DisplayName("생성 테스트 ")
    void createTest() {
        for (Xpoint xpoint : Xpoint.values()) {
            for (Ypoint ypoint : Ypoint.values()) {
                assertThat(Position.valueOf(xpoint.getName() + ypoint.getValue())).isInstanceOf(Position.class);
            }
        }
    }

    @Test
    @DisplayName("잘못된 생성 테스트 ")
    void wrongPositionExceptionTest() {
        assertThatThrownBy(() -> Position.valueOf("z1")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("up 생성 테스트")
    void upTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.up()).isEqualTo(Position.valueOf("c6"));
        Position endOfTopPosition = Position.valueOf("c8");
        assertThat(endOfTopPosition.up()).isEqualTo(Position.valueOf("c8"));
    }

    @Test
    @DisplayName("down 생성 테스트")
    void downTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.down()).isEqualTo(Position.valueOf("c4"));
        Position endOfBottomPosition = Position.valueOf("c1");
        assertThat(endOfBottomPosition.down()).isEqualTo(Position.valueOf("c1"));
    }

    @Test
    @DisplayName("left 생성 테스트")
    void leftTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.left()).isEqualTo(Position.valueOf("b5"));
        Position endOfLeftPosition = Position.valueOf("a5");
        assertThat(endOfLeftPosition.left()).isEqualTo(Position.valueOf("a5"));
    }

    @Test
    @DisplayName("right 생성 테스트")
    void rightTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.right()).isEqualTo(Position.valueOf("d5"));
        Position endOfRightPosition = Position.valueOf("h5");
        assertThat(endOfRightPosition.right()).isEqualTo(Position.valueOf("h5"));
    }

    @Test
    @DisplayName("11시 방향 대각선 테스트")
    void leftUpTest() {
        assertThat(Position.valueOf("c5").leftUp()).isEqualTo(Position.valueOf("b6"));
        assertThat(Position.valueOf("b8").leftUp()).isEqualTo(Position.valueOf("b8"));
        assertThat(Position.valueOf("a8").leftUp()).isEqualTo(Position.valueOf("a8"));
        assertThat(Position.valueOf("a5").leftUp()).isEqualTo(Position.valueOf("a5"));
    }

    @Test
    @DisplayName("1시 방향 대각선 테스트")
    void rightUpTest() {
        assertThat(Position.valueOf("c5").rightUp()).isEqualTo(Position.valueOf("d6"));
        assertThat(Position.valueOf("h5").rightUp()).isEqualTo(Position.valueOf("h5"));
        assertThat(Position.valueOf("c8").rightUp()).isEqualTo(Position.valueOf("c8"));
        assertThat(Position.valueOf("h8").rightUp()).isEqualTo(Position.valueOf("h8"));
    }

    @Test
    @DisplayName("7시 방향 대각선 테스트")
    void leftDownTest() {
        assertThat(Position.valueOf("c5").leftDown()).isEqualTo(Position.valueOf("b4"));
        assertThat(Position.valueOf("a5").leftDown()).isEqualTo(Position.valueOf("a5"));
        assertThat(Position.valueOf("c1").leftDown()).isEqualTo(Position.valueOf("c1"));
        assertThat(Position.valueOf("a1").leftDown()).isEqualTo(Position.valueOf("a1"));
    }

    @Test
    @DisplayName("5시 방향 대각선 테스트")
    void rightDownTest() {
        assertThat(Position.valueOf("c5").rightDown()).isEqualTo(Position.valueOf("d4"));
        assertThat(Position.valueOf("h5").rightDown()).isEqualTo(Position.valueOf("h5"));
        assertThat(Position.valueOf("c1").rightDown()).isEqualTo(Position.valueOf("c1"));
        assertThat(Position.valueOf("h1").rightDown()).isEqualTo(Position.valueOf("h1"));
    }
}
