package chess.domain.board.position;

import static chess.domain.board.position.File.A;
import static chess.domain.board.position.File.B;
import static chess.domain.board.position.File.C;
import static chess.domain.board.position.File.D;
import static chess.domain.board.position.Rank.FOUR;
import static chess.domain.board.position.Rank.ONE;
import static chess.domain.board.position.Rank.THREE;
import static chess.domain.board.position.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    @Test
    @DisplayName("위치 값의 동일성을 테스트한다.")
    void position_equals() {
        //given
        Position position = Position.of(A, ONE);

        //then
        assertThat(position).isEqualTo(Position.of(A, ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "i7", "a0", "A1"})
    @DisplayName("위치 범위를 벗어나면 예외를 던진다.")
    void position_exception() {
        assertThatThrownBy(() -> Position.of("a9"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    void position_find_by_different_ways() {
        Position position1 = Position.of(A, ONE);
        Position position2 = Position.of(1, 1);
        Position position3 = Position.of("a1");

        assertThat(position1).isEqualTo(position2);
        assertThat(position2).isEqualTo(position3);
        assertThat(position3).isEqualTo(position2);
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(대각).")
    void traceGroupDiagonal() {
        //given
        Position source = Position.of(A, ONE);
        Position target = Position.of(D, FOUR);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Position.of(B, TWO), Position.of(C, THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 우측).")
    void traceGroupRank_right() {
        //given
        Position source = Position.of(A, ONE);
        Position target = Position.of(D, ONE);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Position.of(B, ONE), Position.of(C, ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 좌측).")
    void traceGroupRank_left() {
        //given
        Position source = Position.of(D, ONE);
        Position target = Position.of(A, ONE);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Position.of(B, ONE), Position.of(C, ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 위쪽).")
    void traceGroupFile_up() {
        //given
        Position source = Position.of(A, ONE);
        Position target = Position.of(A, FOUR);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Position.of(A, TWO), Position.of(A, THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 아래쪽).")
    void traceGroupFile_down() {
        //given
        Position source = Position.of(A, FOUR);
        Position target = Position.of(A, ONE);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Position.of(A, TWO), Position.of(A, THREE));
    }
}
