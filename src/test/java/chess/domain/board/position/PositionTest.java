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
        Position position = Positions.findPositionBy(A, ONE);

        //then
        assertThat(position).isEqualTo(Positions.findPositionBy(A, ONE));
    }

    @ParameterizedTest
    @ValueSource(strings = {"a9", "i7", "a0", "A1"})
    @DisplayName("위치 범위를 벗어나면 예외를 던진다.")
    void position_exception() {
        assertThatThrownBy(() -> Positions.findPositionBy("a9"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR]");
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(대각).")
    void traceGroupDiagonal() {
        //given
        Position source = Positions.findPositionBy(A, ONE);
        Position target = Positions.findPositionBy(D, FOUR);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Positions.findPositionBy(B, TWO), Positions.findPositionBy(C, THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 우측).")
    void traceGroupRank_right() {
        //given
        Position source = Positions.findPositionBy(A, ONE);
        Position target = Positions.findPositionBy(D, ONE);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Positions.findPositionBy(B, ONE), Positions.findPositionBy(C, ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(가로 좌측).")
    void traceGroupRank_left() {
        //given
        Position source = Positions.findPositionBy(D, ONE);
        Position target = Positions.findPositionBy(A, ONE);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Positions.findPositionBy(B, ONE), Positions.findPositionBy(C, ONE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 위쪽).")
    void traceGroupFile_up() {
        //given
        Position source = Positions.findPositionBy(A, ONE);
        Position target = Positions.findPositionBy(A, FOUR);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Positions.findPositionBy(A, TWO), Positions.findPositionBy(A, THREE));
    }

    @Test
    @DisplayName("두 Position 사이에 위치하는 모든 Position을 담아 List로 반환한다(세로 아래쪽).")
    void traceGroupFile_down() {
        //given
        Position source = Positions.findPositionBy(A, FOUR);
        Position target = Positions.findPositionBy(A, ONE);

        List<Position> traceGroup = source.findPositionsToMove(target);

        //then
        assertThat(traceGroup).contains(Positions.findPositionBy(A, TWO), Positions.findPositionBy(A, THREE));
    }
}
