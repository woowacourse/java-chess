package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.piece.MoveVector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PointTest {

    @Test
    @DisplayName("포인트 객체 생성")
    void generatePoint() {
        assertThat(Point.of("a1")).isEqualTo(Point.of("a1"));
        assertThat(Point.of("h8")).isEqualTo(Point.of("h8"));
    }

    @Test
    @DisplayName("유효하지 않은 포인트 생성시 예외를 반환")
    void validatePoint() {
        assertThatIllegalArgumentException().isThrownBy(() -> Point.of("a9"));
        assertThatIllegalArgumentException().isThrownBy(() -> Point.of("i1"));
    }


    @Test
    @DisplayName("캐싱한 값을 가져오는지 테스트")
    void getCachePoint() {
        assertThat(Point.of("a1")).isSameAs(Point.of("a1"));
    }

    @Test
    @DisplayName("y축 대칭인 점 반환")
    void yAxisSymmetricPoint() {
        Assertions.assertThat(Point.of("b3").yAxisSymmetricPoint()).isEqualTo(Point.of("b6"));
    }

    @Test
    @DisplayName("원점 대칭인 점 반환")
    void originSymmetricPoint() {
        assertThat(Point.of("b3").originSymmetricPoint()).isEqualTo(Point.of("g6"));
    }

    @Test
    @DisplayName("열 차이 반환")
    void columnDifference() {
        assertThat(Point.of("b3").columnDifference(Point.of("c6"))).isEqualTo(-1);
    }

    @Test
    @DisplayName("행 차이 반환")
    void rowDifference() {
        assertThat(Point.of("b3").rowDifference(Point.of("c6"))).isEqualTo(-3);
    }

    @Test
    @DisplayName("벡터만큼 움직인 점 반환")
    void movedPoint() {
        assertThat(Point.of("b3").movedPoint(MoveVector.NEE)).isEqualTo(Point.of("d4"));
    }
}
