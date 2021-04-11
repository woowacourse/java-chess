package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {
    @ParameterizedTest
    @CsvSource(value = {"a8,0,7", "a7,0,6", "a6,0,5", "c6,2,5", "h1,7,0"})
    @DisplayName("사용자가 체스 판에 있는 문자를 입력하면, 알맞은 Position 객체를 반환한다")
    void position_with_user_input(final String input, final int x, final int y) {
        Position userInputPosition = Position.of(input);
        Position boardPosition = new Position(x, y);
        assertThat(userInputPosition).isEqualTo(boardPosition);
    }

    @ParameterizedTest
    @CsvSource(value = {"z1", "8a", "호호"})
    @DisplayName("사용자가 체스 판에 있는 문자를 입력하면, 알맞은 Position 객체를 반환한다")
    void position_with_user_input(final String input) {
        assertThatCode(() -> Position.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판에 없는 칸입니다");
    }

    @Test
    @DisplayName("대각선으로 움직일 시작점과 종점을 입력하면, 그 사이 경로의 Position 객체를 반환한다.")
    void position_diagonal_path_test() {
        final Position start = new Position(0, 0);
        final Position destination = new Position(3, 3);

        List<Position> expectedPath = Arrays.asList(new Position(1, 1), new Position(2, 2));
        List<Position> resultPath = start.generateDiagonalPath(destination);

        assertEquals(expectedPath, resultPath);
    }

    @Test
    @DisplayName("직선으로 움직일 시작점과 종점을 입력하면, 그 사이 경로의 Position 객체를 반환한다.")
    void position_straight_path_test() {
        final Position start = new Position(0, 0);
        final Position destination = new Position(3, 0);

        List<Position> expectedPath = Arrays.asList(new Position(1, 0), new Position(2, 0));
        List<Position> resultPath = start.generateStraightPath(destination);

        assertEquals(expectedPath, resultPath);
    }

    @Test
    @DisplayName("현재 Position에 대응하는 체스판의 위치를 정상 반환한다.")
    void position_initial_test() {
        Position position = new Position(0, 0);
        assertThat(position.getPositionInitial()).isEqualTo("a1");

        position = new Position(7, 7);
        assertThat(position.getPositionInitial()).isEqualTo("h8");
    }
}
