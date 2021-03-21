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
                assertThat(Position.of(xpoint.getName() + ypoint.getValue())).isInstanceOf(Position.class);
            }
        }
    }

    @Test
    @DisplayName("잘못된 생성 테스트 ")
    void wrongPositionExceptionTest() {
        assertThatThrownBy(() -> Position.of("z1")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("up 생성 테스트")
    void upTest() {
        Position position = Position.of("c5");
        assertThat(position.up()).isEqualTo(Position.of("c6"));
        Position endOfTopPosition = Position.of("c8");
        assertThat(endOfTopPosition.up()).isEqualTo(Position.of("c8"));
    }

    @Test
    @DisplayName("down 생성 테스트")
    void downTest() {
        Position position = Position.of("c5");
        assertThat(position.down()).isEqualTo(Position.of("c4"));
        Position endOfBottomPosition = Position.of("c1");
        assertThat(endOfBottomPosition.down()).isEqualTo(Position.of("c1"));
    }

    @Test
    @DisplayName("left 생성 테스트")
    void leftTest() {
        Position position = Position.of("c5");
        assertThat(position.left()).isEqualTo(Position.of("b5"));
        Position endOfLeftPosition = Position.of("a5");
        assertThat(endOfLeftPosition.left()).isEqualTo(Position.of("a5"));
    }

    @Test
    @DisplayName("right 생성 테스트")
    void rightTest() {
        Position position = Position.of("c5");
        assertThat(position.right()).isEqualTo(Position.of("d5"));
        Position endOfRightPosition = Position.of("h5");
        assertThat(endOfRightPosition.right()).isEqualTo(Position.of("h5"));
    }

    @Test
    @DisplayName("11시 방향 대각선 테스트")
    void leftUpTest() {
        assertThat(Position.of("c5").leftUp()).isEqualTo(Position.of("b6"));
        assertThat(Position.of("b8").leftUp()).isEqualTo(Position.of("b8"));
        assertThat(Position.of("a8").leftUp()).isEqualTo(Position.of("a8"));
        assertThat(Position.of("a5").leftUp()).isEqualTo(Position.of("a5"));
    }

    @Test
    @DisplayName("1시 방향 대각선 테스트")
    void rightUpTest() {
        assertThat(Position.of("c5").rightUp()).isEqualTo(Position.of("d6"));
        assertThat(Position.of("h5").rightUp()).isEqualTo(Position.of("h5"));
        assertThat(Position.of("c8").rightUp()).isEqualTo(Position.of("c8"));
        assertThat(Position.of("h8").rightUp()).isEqualTo(Position.of("h8"));
    }

    @Test
    @DisplayName("7시 방향 대각선 테스트")
    void leftDownTest() {
        assertThat(Position.of("c5").leftDown()).isEqualTo(Position.of("b4"));
        assertThat(Position.of("a5").leftDown()).isEqualTo(Position.of("a5"));
        assertThat(Position.of("c1").leftDown()).isEqualTo(Position.of("c1"));
        assertThat(Position.of("a1").leftDown()).isEqualTo(Position.of("a1"));
    }

    @Test
    @DisplayName("5시 방향 대각선 테스트")
    void rightDownTest() {
        assertThat(Position.of("c5").rightDown()).isEqualTo(Position.of("d4"));
        assertThat(Position.of("h5").rightDown()).isEqualTo(Position.of("h5"));
        assertThat(Position.of("c1").rightDown()).isEqualTo(Position.of("c1"));
        assertThat(Position.of("h1").rightDown()).isEqualTo(Position.of("h1"));
    }

    @Test
    @DisplayName("12시 방향 벡터 테스트")
    void upVectorTest() {
        assertThat(Position.of("c8").upVector()).containsExactly();
        assertThat(Position.of("c1").upVector()).containsExactly(
                Position.of("c2"), Position.of("c3"),
                Position.of("c4"), Position.of("c5"),
                Position.of("c6"), Position.of("c7"),
                Position.of("c8"));
    }

    @Test
    @DisplayName("6시 방향 벡터 테스트")
    void downVectorTest() {
        assertThat(Position.of("c1").downVector()).containsExactly();
        assertThat(Position.of("c8").downVector()).containsExactly(
                Position.of("c7"), Position.of("c6"),
                Position.of("c5"), Position.of("c4"),
                Position.of("c3"), Position.of("c2"),
                Position.of("c1"));
    }

    @Test
    @DisplayName("9시 방향 벡터 테스트")
    void leftVectorTest() {
        assertThat(Position.of("a5").leftVector()).containsExactly();
        assertThat(Position.of("h5").leftVector()).containsExactly(
                Position.of("g5"), Position.of("f5"),
                Position.of("e5"), Position.of("d5"),
                Position.of("c5"), Position.of("b5"),
                Position.of("a5"));
    }

    @Test
    @DisplayName("3시 방향 벡터 테스트")
    void rightVectorTest() {
        assertThat(Position.of("h5").rightVector()).containsExactly();
        assertThat(Position.of("a5").rightVector()).containsExactly(
                Position.of("b5"), Position.of("c5"),
                Position.of("d5"), Position.of("e5"),
                Position.of("f5"), Position.of("g5"),
                Position.of("h5"));
    }

    @Test
    @DisplayName("11시 방향 벡터 테스트")
    void leftUpVectorTest(){
        assertThat(Position.of("a8").leftUpVector()).containsExactly();
        assertThat(Position.of("h1").leftUpVector()).containsExactly(
            Position.of("g2"), Position.of("f3"), Position.of("e4"),
            Position.of("d5"), Position.of("c6"), Position.of("b7"),
            Position.of("a8"));
    }

    @Test
    @DisplayName("7시 방향 벡터 테스트")
    void leftDownVectorTest(){
        assertThat(Position.of("a1").leftDownVector()).containsExactly();
        assertThat(Position.of("h8").leftDownVector()).containsExactly(
            Position.of("g7"), Position.of("f6"), Position.of("e5"),
            Position.of("d4"), Position.of("c3"), Position.of("b2"),
            Position.of("a1"));
    }

    @Test
    @DisplayName("1시 방향 벡터 테스트")
    void rightUpVectorTest(){
        assertThat(Position.of("h8").rightUpVector()).containsExactly();
        assertThat(Position.of("a1").rightUpVector()).containsExactly(
            Position.of("b2"), Position.of("c3"), Position.of("d4"),
            Position.of("e5"), Position.of("f6"), Position.of("g7"),
            Position.of("h8"));
    }

    @Test
    @DisplayName("5시 방향 벡터 테스트")
    void rightDownVectorTest(){
        assertThat(Position.of("h1").rightDownVector()).containsExactly();
        assertThat(Position.of("a8").rightDownVector()).containsExactly(
            Position.of("b7"), Position.of("c6"), Position.of("d5"),
            Position.of("e4"), Position.of("f3"), Position.of("g2"),
            Position.of("h1"));
    }

    @Test
    @DisplayName("UUL 이동 테스트")
    void upUpLeftTest(){
        assertThat(Position.of("c5").upUpLeft()).isEqualTo(Position.of("b7"));
        assertThat(Position.of("a5").upUpLeft()).isEqualTo(Position.of("a5"));
        assertThat(Position.of("c8").upUpLeft()).isEqualTo(Position.of("c8"));
        assertThat(Position.of("a8").upUpLeft()).isEqualTo(Position.of("a8"));
    }

    @Test
    @DisplayName("UUR 이동 테스트")
    void downDownLeftTest(){
        assertThat(Position.of("c5").upUpRight()).isEqualTo(Position.of("d7"));
        assertThat(Position.of("h5").upUpRight()).isEqualTo(Position.of("h5"));
        assertThat(Position.of("c8").upUpRight()).isEqualTo(Position.of("c8"));
        assertThat(Position.of("h8").upUpRight()).isEqualTo(Position.of("h8"));
    }

    @Test
    @DisplayName("DDL 이동 테스트")
    void downDownRightTest(){
        assertThat(Position.of("c5").downDownLeft()).isEqualTo(Position.of("b3"));
        assertThat(Position.of("a5").downDownLeft()).isEqualTo(Position.of("a5"));
        assertThat(Position.of("c1").downDownLeft()).isEqualTo(Position.of("c1"));
        assertThat(Position.of("a1").downDownLeft()).isEqualTo(Position.of("a1"));
    }

    @Test
    @DisplayName("DDR 이동 테스트")
    void upUpRightTest(){
        assertThat(Position.of("c5").downDownRight()).isEqualTo(Position.of("d3"));
        assertThat(Position.of("h5").downDownRight()).isEqualTo(Position.of("h5"));
        assertThat(Position.of("c1").downDownRight()).isEqualTo(Position.of("c1"));
        assertThat(Position.of("h1").downDownRight()).isEqualTo(Position.of("h1"));
    }

    @Test
    @DisplayName("LLU 이동 테스트")
    void leftLeftUpTest(){
        assertThat(Position.of("c5").leftLeftUp()).isEqualTo(Position.of("a6"));
        assertThat(Position.of("a5").leftLeftUp()).isEqualTo(Position.of("a5"));
        assertThat(Position.of("c8").leftLeftUp()).isEqualTo(Position.of("c8"));
        assertThat(Position.of("a8").leftLeftUp()).isEqualTo(Position.of("a8"));
    }

    @Test
    @DisplayName("LLD 이동 테스트")
    void leftLeftDownTest(){
        assertThat(Position.of("c5").leftLeftDown()).isEqualTo(Position.of("a4"));
        assertThat(Position.of("a5").leftLeftDown()).isEqualTo(Position.of("a5"));
        assertThat(Position.of("c1").leftLeftDown()).isEqualTo(Position.of("c1"));
        assertThat(Position.of("a1").leftLeftDown()).isEqualTo(Position.of("a1"));
    }

    @Test
    @DisplayName("RRU 이동 테스트")
    void rightRightUpTest(){
        assertThat(Position.of("c5").rightRightUp()).isEqualTo(Position.of("e6"));
        assertThat(Position.of("h5").rightRightUp()).isEqualTo(Position.of("h5"));
        assertThat(Position.of("c8").rightRightUp()).isEqualTo(Position.of("c8"));
        assertThat(Position.of("h8").rightRightUp()).isEqualTo(Position.of("h8"));
    }

    @Test
    @DisplayName("RRD 이동 테스트")
    void rightRightDownTest(){
        assertThat(Position.of("c5").rightRightDown()).isEqualTo(Position.of("e4"));
        assertThat(Position.of("h5").rightRightDown()).isEqualTo(Position.of("h5"));
        assertThat(Position.of("c1").rightRightDown()).isEqualTo(Position.of("c1"));
        assertThat(Position.of("h1").rightRightDown()).isEqualTo(Position.of("h1"));
    }
}
