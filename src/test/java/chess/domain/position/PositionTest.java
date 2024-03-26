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
        var result = Position.from("b", "2");
        assertThat(result).isInstanceOf(Position.class);
    }

    @Test
    @DisplayName("잘못된 Position을 넘기면 에러가 발생한다.")
    void wrongValueTest() {
        assertThatThrownBy(() -> {
            Position.from("x", "5");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a, 1, 0, 1, a, 2",
            "a, 2, 0, -1, a, 1",
            "a, 1, 1, 0, b, 1",
            "b, 1, -1, 0, a, 1",
            "b, 1, -1, 1, a, 2",
            "a, 1, 1, 1, b, 2",
            "b, 2, -1, -1, a, 1",
            "a, 2, 1, -1, b, 1"})
    @DisplayName("전달한 방향으로 이동한 새로운 Position을 반환한다.")
    void Position_Move_with_direction(String file1,
                                      String rank1,
                                      int fileDirection,
                                      int rankDirection,
                                      String file2,
                                      String rank2) {
        Position source = Position.from(file1, rank1);
        Position target = Position.from(file2, rank2);
        var result = source.move(fileDirection, rankDirection);
        assertThat(result).isEqualTo(target);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a, 1, b, 3, 2",
            "b, 2, b, 5, 3",
            "b, 4, b, 2, 2",
            "b, 5, a, 2, 3"})
    @DisplayName("두 포지션 사이의 세로 길이를 구한다.")
    void Position_Calculate_rank_distance_with_other_position(String file1,
                                                              String rank1,
                                                              String file2,
                                                              String rank2,
                                                              int distance) {
        Position source = Position.from(file1, rank1);
        Position target = Position.from(file2, rank2);
        var result = source.calculateRankDistance(target);
        assertThat(result).isEqualTo(distance);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a, 1, b, 3, 1",
            "b, 2, e, 2, 3",
            "e, 4, b, 4, 3",
            "b, 3, a, 1, 1"})
    @DisplayName("두 포지션 사이의 가로 길이를 구한다.")
    void Position_Calculate_file_distance_with_other_position(String file1,
                                                              String rank1,
                                                              String file2,
                                                              String rank2,
                                                              int distance) {
        Position source = Position.from(file1, rank1);
        Position target = Position.from(file2, rank2);
        var result = source.calculateFileDistance(target);
        assertThat(result).isEqualTo(distance);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a, 1, b, 1, true",
            "a, 2, a, 4, false"})
    @DisplayName("두 포지션이 같은 file인지 확인한다.")
    void Position_Check_is_same_rank(String file1,
                                     String rank1,
                                     String file2,
                                     String rank2,
                                     boolean isSame) {
        Position source = Position.from(file1, rank1);
        Position target = Position.from(file2, rank2);
        var result = source.isSameRank(target);
        assertThat(result).isEqualTo(isSame);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a, 1, b, 1, false",
            "a, 2, a, 4, true"})
    @DisplayName("두 포지션이 같은 file인지 확인한다.")
    void Position_Check_is_same_file(String file1,
                                     String rank1,
                                     String file2,
                                     String rank2,
                                     boolean isSame) {
        Position source = Position.from(file1, rank1);
        Position target = Position.from(file2, rank2);
        var result = source.isSameFile(target);
        assertThat(result).isEqualTo(isSame);
    }
}
