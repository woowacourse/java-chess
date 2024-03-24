package chess.domain.position;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {
    @Test
    @DisplayName("Position을 생성한다.")
    void makePosition() {
        var result = Position.from("b2");
        assertThat(result).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("잘못된 Position을 넘기면 에러가 발생한다.")
    void wrongValueTest() {
        assertThatThrownBy(() -> {
            Position.from("x5");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a1, 0, 1, a2",
            "a2, 0, -1, a1",
            "a1, 1, 0, b1",
            "b1, -1, 0, a1",
            "b1, -1, 1, a2",
            "a1, 1, 1, b2",
            "b2, -1, -1, a1",
            "a2, 1, -1, b1"})
    @DisplayName("전달한 방향으로 이동한 새로운 Position을 반환한다.")
    void Position_Move_with_direction(Position source,
                                      int fileDirection,
                                      int rankDirection,
                                      Position target) {
        var result = source.move(fileDirection, rankDirection);
        assertThat(result).isEqualTo(target);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a1, b3, 2",
            "b2, b5, 3",
            "b4, b2, 2",
            "b5, a2, 3"})
    @DisplayName("두 포지션 사이의 세로 길이를 구한다.")
    void Position_Calculate_rank_distance_with_other_position(Position source,
                                                                Position target,
                                                                int distance) {
        var result = source.calculateRankDistance(target);
        assertThat(result).isEqualTo(distance);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a1, b3, 1",
            "b2, e2, 3",
            "e4, b4, 3",
            "b3, a1, 1"})
    @DisplayName("두 포지션 사이의 가로 길이를 구한다.")
    void Position_Calculate_file_distance_with_other_position(Position source,
                                                                Position target,
                                                                int distance) {
        var result = source.calculateFileDistance(target);
        assertThat(result).isEqualTo(distance);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a1, b1, true",
            "a2, a4, false"})
    @DisplayName("두 포지션이 같은 file인지 확인한다.")
    void Position_Check_is_same_rank(Position source, Position target, boolean isSame) {
        var result = source.isSameRank(target);
        assertThat(result).isEqualTo(isSame);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a1, b1, false",
            "a2, a4, true"})
    @DisplayName("두 포지션이 같은 file인지 확인한다.")
    void Position_Check_is_same_file(Position source, Position target, boolean isSame) {
        var result = source.isSameFile(target);
        assertThat(result).isEqualTo(isSame);
    }
}
