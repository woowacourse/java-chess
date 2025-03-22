package chess.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.Color;
import chess.Fixtures;
import chess.Position;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnightTest {

    Knight knight;

    @BeforeEach
    void initKnight() {
        knight = new Knight(Color.WHITE, Fixtures.D4);
    }

    @DisplayName("말은 상하좌우 한 칸과 대각선으로 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("knightDestination")
    void move_up_and_up_right(int x, int y, Position dest) {
        knight.move(x, y);

        assertThat(knight.getPosition()).isEqualTo(dest);
    }

    static Stream<Arguments> knightDestination() {
        return Stream.of(
                Arguments.of(1, 2, Fixtures.E6),
                Arguments.of(-1, 2, Fixtures.C6),
                Arguments.of(1, -2, Fixtures.E2),
                Arguments.of(-1, -2, Fixtures.C2),
                Arguments.of(2, 1, Fixtures.F5),
                Arguments.of(2, -1, Fixtures.F3),
                Arguments.of(-2, 1, Fixtures.B5),
                Arguments.of(-2, -1, Fixtures.B3)
        );
    }

    @DisplayName("말은 상하좌우 한 칸과 대각선이 아니면 이동이 불가능하다.")
    @ParameterizedTest
    @MethodSource("notKnightMovingRule")
    void move_up_and_up_right(int x, int y) {
        assertThatThrownBy(() -> knight.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> notKnightMovingRule() {
        return Stream.of(
                Arguments.of(2, 2),
                Arguments.of(0, 2),
                Arguments.of(2, 0),
                Arguments.of(3, 2),
                Arguments.of(2, 3),
                Arguments.of(5, 2)
        );
    }

    @DisplayName("말은 장기판 밖으로 이동할 수 없다.")
    @ParameterizedTest
    @MethodSource("outOfBoardMove")
    void cant_move_out_of_board(int x, int y) {
        Knight knight_A1 = new Knight(Color.WHITE, Fixtures.A1);

        assertThatThrownBy(() -> knight_A1.move(x, y))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> outOfBoardMove() {
        return Stream.of(
                Arguments.of(-1, 2),
                Arguments.of(1, -2),
                Arguments.of(-1, -2),
                Arguments.of(2, -1),
                Arguments.of(-2, 1),
                Arguments.of(-2, -1)
        );
    }
}
