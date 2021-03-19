package chess.domain.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    @DisplayName("좌표 묶음 생성하기")
    void generateTest() {
        assertThat(Position.generate()).containsAll(testPositions());
    }

    public static List<Position> testPositions(){
        List<Position> positions = new ArrayList<>();
        positions.add(Position.valueOf("a8"));
        positions.add(Position.valueOf("b8"));
        positions.add(Position.valueOf("c8"));
        positions.add(Position.valueOf("d8"));
        positions.add(Position.valueOf("e8"));
        positions.add(Position.valueOf("f8"));
        positions.add(Position.valueOf("g8"));
        positions.add(Position.valueOf("h8"));

        positions.add(Position.valueOf("a7"));
        positions.add(Position.valueOf("b7"));
        positions.add(Position.valueOf("c7"));
        positions.add(Position.valueOf("d7"));
        positions.add(Position.valueOf("e7"));
        positions.add(Position.valueOf("f7"));
        positions.add(Position.valueOf("g7"));
        positions.add(Position.valueOf("h7"));

        positions.add(Position.valueOf("a6"));
        positions.add(Position.valueOf("b6"));
        positions.add(Position.valueOf("c6"));
        positions.add(Position.valueOf("d6"));
        positions.add(Position.valueOf("e6"));
        positions.add(Position.valueOf("f6"));
        positions.add(Position.valueOf("g6"));
        positions.add(Position.valueOf("h6"));

        positions.add(Position.valueOf("a5"));
        positions.add(Position.valueOf("b5"));
        positions.add(Position.valueOf("c5"));
        positions.add(Position.valueOf("d5"));
        positions.add(Position.valueOf("e5"));
        positions.add(Position.valueOf("f5"));
        positions.add(Position.valueOf("g5"));
        positions.add(Position.valueOf("h5"));

        positions.add(Position.valueOf("a4"));
        positions.add(Position.valueOf("b4"));
        positions.add(Position.valueOf("c4"));
        positions.add(Position.valueOf("d4"));
        positions.add(Position.valueOf("e4"));
        positions.add(Position.valueOf("f4"));
        positions.add(Position.valueOf("g4"));
        positions.add(Position.valueOf("h4"));

        positions.add(Position.valueOf("a3"));
        positions.add(Position.valueOf("b3"));
        positions.add(Position.valueOf("c3"));
        positions.add(Position.valueOf("d3"));
        positions.add(Position.valueOf("e3"));
        positions.add(Position.valueOf("f3"));
        positions.add(Position.valueOf("g3"));
        positions.add(Position.valueOf("h3"));

        positions.add(Position.valueOf("a2"));
        positions.add(Position.valueOf("b2"));
        positions.add(Position.valueOf("c2"));
        positions.add(Position.valueOf("d2"));
        positions.add(Position.valueOf("e2"));
        positions.add(Position.valueOf("f2"));
        positions.add(Position.valueOf("g2"));
        positions.add(Position.valueOf("h2"));

        positions.add(Position.valueOf("a1"));
        positions.add(Position.valueOf("b1"));
        positions.add(Position.valueOf("c1"));
        positions.add(Position.valueOf("d1"));
        positions.add(Position.valueOf("e1"));
        positions.add(Position.valueOf("f1"));
        positions.add(Position.valueOf("g1"));
        positions.add(Position.valueOf("h1"));

        return positions;
    }

    @Test
    @DisplayName("up 생성 테스트")
    void upTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.up()).isEqualTo(Position.valueOf("c6"));
        Position errorTestPosition = Position.valueOf("c8");
        assertThatThrownBy(errorTestPosition::up).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("down 생성 테스트")
    void downTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.down()).isEqualTo(Position.valueOf("c4"));
        Position errorTestPosition = Position.valueOf("c1");
        assertThatThrownBy(errorTestPosition::down).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("left 생성 테스트")
    void leftTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.left()).isEqualTo(Position.valueOf("b5"));
        Position errorTestPosition = Position.valueOf("a5");
        assertThatThrownBy(errorTestPosition::left).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("right 생성 테스트")
    void rightTest() {
        Position position = Position.valueOf("c5");
        assertThat(position.right()).isEqualTo(Position.valueOf("d5"));
        Position errorTestPosition = Position.valueOf("h5");
        assertThatThrownBy(errorTestPosition::right).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("11시 방향 대각선 테스트")
    void leftUpTest() {
        assertThat(Position.valueOf("c5").leftUp()).isEqualTo(Position.valueOf("b6"));
        assertThatThrownBy(()->Position.valueOf("a5").leftUp()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("b8").leftUp()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("a8").leftUp()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1시 방향 대각선 테스트")
    void rightUpTest() {
        assertThat(Position.valueOf("c5").rightUp()).isEqualTo(Position.valueOf("d6"));
        assertThatThrownBy(()->Position.valueOf("h5").rightUp()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("b8").rightUp()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("h8").rightUp()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("7시 방향 대각선 테스트")
    void leftDownTest() {
        assertThat(Position.valueOf("c5").downLeft()).isEqualTo(Position.valueOf("b4"));
        assertThatThrownBy(()->Position.valueOf("a5").downLeft()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("b1").downLeft()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("a1").downLeft()).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("5시 방향 대각선 테스트")
    void rightDownTest() {
        assertThat(Position.valueOf("c5").downRight()).isEqualTo(Position.valueOf("d4"));
        assertThatThrownBy(()->Position.valueOf("h5").downRight()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("b1").downRight()).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->Position.valueOf("h1").downRight()).isInstanceOf(IllegalArgumentException.class);
    }
}
