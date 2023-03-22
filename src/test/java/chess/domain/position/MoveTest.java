package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class MoveTest {

    @DisplayName("소스와 타겟 포지션이 일치할경우 예외가 발생한다")
    @Test
    void sourceEqualsTarget_throws() {
        assertThatThrownBy(() -> new Move(new Position("c1"), new Position("c1")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일한 포지션으로의 움직임을 생성할 수 없습니다.");
    }

    @DisplayName("대각선인지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"c2", "b1", "c4", "b5", "e4", "f5", "e2", "f1"})
    void isDiagonal(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);
        Move move = new Move(position1, position2);

        assertThat(move.isDiagonal()).isTrue();
    }

    @DisplayName("대각선이 아닌지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"d4", "d2", "c3", "c5"})
    void isNotDiagonal(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);
        Move move = new Move(position1, position2);

        assertThat(move.isDiagonal()).isFalse();
    }

    @DisplayName("수평/수직선인지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"c3", "b3", "e3", "f3", "d4", "d5", "d2", "d1"})
    void isStraight(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);
        Move move = new Move(position1, position2);

        assertThat(move.isStraight()).isTrue();
    }

    @DisplayName("수평/수직선이 아닌지 판단한다")
    @ParameterizedTest
    @ValueSource(strings = {"c4", "c5"})
    void isNotStraight(String position) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);
        Move move = new Move(position1, position2);

        assertThat(move.isStraight()).isFalse();
    }

    @DisplayName("직선 경로를 찾을 수 있다")
    @Test
    void findRoute_straight() {
        Position position1 = new Position("d3");
        Position position2 = new Position("d7");
        Move move = new Move(position1, position2);

        assertThat(move.findRoute())
                .containsExactlyInAnyOrder(new Position("d4"), new Position("d5"), new Position("d6"));
    }

    @DisplayName("대각선 경로를 찾을 수 있다")
    @Test
    void findRoute_diagonal() {
        Position position1 = new Position("d3");
        Position position2 = new Position("a6");
        Move move = new Move(position1, position2);

        assertThat(move.findRoute())
                .containsExactlyInAnyOrder(new Position("c4"), new Position("b5"));
    }

    @DisplayName("직선과 대각선이 아닌 경우 빈 경로를 리턴한다")
    @Test
    void findRoute_notDiagonalOrStraight_throws() {
        Position position1 = new Position("d3");
        Position position2 = new Position("c5");
        Move move = new Move(position1, position2);

        assertThat(move.findRoute()).isEmpty();
    }

    @DisplayName("파일 변화량을 구할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"f3,2", "b3,-2", "d4,0"})
    void getFileIndex(String position, int deltaFile) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);
        Move move = new Move(position1, position2);

        assertThat(move.getDeltaFile())
                .isEqualTo(deltaFile);
    }

    @DisplayName("랭크 변화량을 구할 수 있다")
    @ParameterizedTest
    @CsvSource(value = {"d5,2", "d1,-2", "e3,0"})
    void getRankIndex(String position, int deltaRank) {
        Position position1 = new Position("d3");
        Position position2 = new Position(position);
        Move move = new Move(position1, position2);

        assertThat(move.getDeltaRank())
                .isEqualTo(deltaRank);
    }
}
