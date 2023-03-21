package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @DisplayName("C3 와 같은 형식으로 생성할 수 있다")
    @Test
    void createPosition_formLike_C3() {
        Position position = new Position("C3");

        assertThat(position)
                .isEqualTo(new Position(File.C, Rank.THREE));
    }

    @DisplayName("c3 와 같은 형식으로 생성할 수 있다")
    @Test
    void createPosition_formLike_c3() {
        Position position = new Position("c3");

        assertThat(position)
                .isEqualTo(new Position(File.C, Rank.THREE));
    }

    @DisplayName("올바르지 않은 형식의 포지션 입력시 예외를 던진다")
    @ParameterizedTest
    @ValueSource(strings = {"3c", "c10", "33", "cc"})
    void invalidFormPosition_throws(String position) {
        assertThatThrownBy(() -> new Position(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 포지션입니다.");
    }

    @DisplayName("대각선인지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"c2", "b1", "c4", "b5", "e4", "f5", "e2", "f1"})
    void isDiagonal(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);

        assertThat(position1.isDiagonal(position2)).isTrue();
    }

    @DisplayName("대각선이 아닌지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"d4", "d2", "c3", "c5"})
    void isNotDiagonal(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);

        assertThat(position1.isDiagonal(position2)).isFalse();
    }

    @DisplayName("수평/수직선인지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "b3", "e3", "f3", "d4", "d5", "d2", "d1"})
    void isStraight(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);

        assertThat(position1.isStraight(position2)).isTrue();
    }


    @DisplayName("수평/수직선이 아닌지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"c4", "c5"})
    void isNotStraight(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);

        assertThat(position1.isStraight(position2)).isFalse();
    }

    @DisplayName("직선 경로를 찾을 수 있다")
    @Test
    void findRoute_straight() {
        Position position1 = new Position("d3");
        Position position2 = new Position("d7");

        assertThat(position1.findRoute(position2))
                .containsExactlyInAnyOrder(new Position("d4"), new Position("d5"), new Position("d6"));
    }

    @DisplayName("대각선 경로를 찾을 수 있다")
    @Test
    void findRoute_diagonal() {
        Position position1 = new Position("d3");
        Position position2 = new Position("a6");

        assertThat(position1.findRoute(position2))
                .containsExactlyInAnyOrder(new Position("c4"), new Position("b5"));
    }

    @DisplayName("직선과 대각선이 아닌 경우 빈 경로를 리턴한다")
    @Test
    void findRoute_notDiagonalOrStraight_throws() {
        Position position1 = new Position("d3");
        Position position2 = new Position("c5");

        assertThat(position1.findRoute(position2)).isEmpty();
    }

    @DisplayName("파일 변화량을 구할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"f3,2", "b3,-2", "d4,0"})
    void getFileIndex(String position, int deltaFile) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);

        assertThat(position1.getDeltaFile(position2))
                .isEqualTo(deltaFile);
    }

    @DisplayName("랭크 변화량을 구할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"d5,2", "d1,-2", "e3,0"})
    void getRankIndex(String position, int deltaRank) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);

        assertThat(position1.getDeltaRank(position2))
                .isEqualTo(deltaRank);
    }
}
